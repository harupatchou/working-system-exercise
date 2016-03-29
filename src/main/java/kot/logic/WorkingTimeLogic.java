package main.java.kot.logic;

import main.java.kot.common.TempTime;
import main.java.kot.common.workingtime.constant.ConstantWorkingTime;
import main.java.kot.common.workingtime.constant.MonthlyLegalWorkingTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 労働時間関連ロジック
 *  @author ueno
 * **/
public class WorkingTimeLogic {


	/*string型からint型の時分オブジェクトを取得*/
	public static TempTime getTimeInt(String str){

		String[] timeArray = str.split(":");
		TempTime temptime = new TempTime();
		temptime.setHour(Integer.parseInt(timeArray[0]));
		temptime.setMinute(Integer.parseInt(timeArray[1]));
		return temptime;
	}

	/*double型からString型の時分に変換(例.0.5→「0:30」)*/
	public static String getTimeDoubleToString(double time){
		int hour = (int)time;
		int minute;
		String strHour = String.valueOf(hour);
		String strMinute = "";
		if(hour == 0){
			minute = (int)(60 * time);
		}else{
			minute = (int)(60 * (time - hour));
		}

		if(minute < 10){
			strMinute = "0" + String.valueOf(minute);
		}else{
			strMinute = String.valueOf(minute);
		}
		String timeString = strHour + ":" + strMinute;
		return timeString;
	}

	/*startからendまでの時間差を算出してString型で返す*/
	public static String getWorkingtimeLag(TempTime start,TempTime end){

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm" );

		//時刻セット用Calendarクラス
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		Calendar workingTimeLag = Calendar.getInstance();

		//出勤時間をセット
		startTime.set(Calendar.HOUR_OF_DAY, start.getHour());
		startTime.set( Calendar.MINUTE, start.getMinute());
		startTime.set( Calendar.SECOND, 00 );

		//退勤時間をセット
		endTime.set( Calendar.HOUR_OF_DAY, end.getHour());
		endTime.set( Calendar.MINUTE, end.getMinute());
		endTime.set( Calendar.SECOND, 00 );

		//実労働時間を算出(差分を求めてUTC+9:00を引く)
		long tempWorkingTimeLag = endTime.getTimeInMillis() - startTime.getTimeInMillis() - workingTimeLag.getTimeZone().getRawOffset();
		workingTimeLag.setTimeInMillis(tempWorkingTimeLag);

		String[] workingTimeArray = sdf.format(workingTimeLag.getTime()).split(":");

		//時の先頭が0のときは0以降の文字列を取得(「00:00」→「0:00」)
		String firstChar = workingTimeArray[0].substring(0, 1);
		if(firstChar.equals("0")){
			workingTimeArray[0] = workingTimeArray[0].substring(1);
		}

		String workingTime = workingTimeArray[0] + ":" + workingTimeArray[1];

		return workingTime;
	}


	/*2つのstring型の時刻から大きいほうの値を返す*/
	//FIXME いいの？これでいいの？
	public static String compareWorkingTime(String compareFirst, String compareSecond){

		TempTime compareTempTimeFirst = getTimeInt(compareFirst);
		TempTime compareTempTimeSecond = getTimeInt(compareSecond);

		String answerCompare = "";
		if(compareTempTimeFirst.getHour() > compareTempTimeSecond.getHour()){
			answerCompare = compareFirst;
		}else if(compareTempTimeSecond.getHour() > compareTempTimeFirst.getHour()){
			answerCompare = compareSecond;
		}else if(compareTempTimeFirst.getHour() == compareTempTimeSecond.getHour() &&
				compareTempTimeFirst.getMinute() > compareTempTimeSecond.getMinute()){
			answerCompare = compareFirst;
		}else if(compareTempTimeSecond.getHour() == compareTempTimeFirst.getHour() &&
				compareTempTimeSecond.getMinute() > compareTempTimeFirst.getMinute()){
			answerCompare = compareSecond;
		}else if(compareTempTimeFirst.getHour() == compareTempTimeSecond.getHour() &&
				compareTempTimeFirst.getMinute() == compareTempTimeSecond.getMinute()){
			answerCompare = "same";
		}

		return answerCompare;
	}

	/*実労働時間と法定労働時間の差を取得してstring型で返す*/
	public static String subtractionWorkingTimeString(String workingTime , String legalWorkingTime){

		String answerSubtraction = "";
		TempTime tempWorkingTime = getTimeInt(workingTime);
		TempTime tempLegalWorkingTime = getTimeInt(legalWorkingTime);

		int hour = tempWorkingTime.getHour() - tempLegalWorkingTime.getHour();
		int minute = tempWorkingTime.getMinute() - tempLegalWorkingTime.getMinute();
		String tempHour = "";
		String tempMinute = "";

		if(minute < 0){
			tempHour = String.valueOf(hour - 1);
			tempMinute = String.valueOf(minute + 60);
		}else if(minute > 0 && minute < 10){
			tempHour = String.valueOf(hour);
			tempMinute = "0" + String.valueOf(minute);
		}else {
			tempHour = String.valueOf(hour);
			tempMinute = String.valueOf(minute);
		}
		answerSubtraction = tempHour + ":" + tempMinute;

		return answerSubtraction;
	}

	/*String型の時刻「0:00」同士の加算*/
	public static String additionWorkingTimeString(String workingTime, String currentWorkingTime){
		String answerAddition = "";

		TempTime tempWorkingTime = getTimeInt(workingTime);
		TempTime tempCurrentWorkingTime = getTimeInt(currentWorkingTime);

		int hour = tempWorkingTime.getHour() + tempCurrentWorkingTime.getHour();
		int minute = tempWorkingTime.getMinute() + tempCurrentWorkingTime.getMinute();
		String tempHour = "";
		String tempMinute = "";

		if(minute >= 60){
			tempHour = String.valueOf(hour + 1);
			tempMinute = String.valueOf(minute - 60);
		}else if(minute < 10){
			tempHour = String.valueOf(hour);
			tempMinute = "0" + String.valueOf(minute);
		}else{
			tempHour = String.valueOf(hour);
			tempMinute = String.valueOf(minute);
		}

		answerAddition = tempHour + ":" + tempMinute;

		return answerAddition;
	}

	/*実労働時間と法定労働時間の差を取得してTempTimeオブジェクトを返す*/
	public static TempTime subtractionWorkingTimeTempTime(String workingTime , String legalWorkingTime){

		TempTime answerSubtraction = new TempTime();
		TempTime tempWorkingTime = getTimeInt(workingTime);
		TempTime tempLegalWorkingTime = getTimeInt(legalWorkingTime);

		int hour = tempWorkingTime.getHour() - tempLegalWorkingTime.getHour();
		int minute = tempWorkingTime.getMinute() - tempLegalWorkingTime.getMinute();

		if(minute < 0){
			hour = hour - 1;
			minute =  minute + 60;
		}

		answerSubtraction.setHour(hour);
		answerSubtraction.setMinute(minute);

		return answerSubtraction;
	}

	/*月の最大日数から月の法定労働時間をセット*/
	public static String getMonthlyLegalWorkingtime(Date date){

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		double tempMonthlyLegalWorkingtime = 0;
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		if(maxDay == 28){
			tempMonthlyLegalWorkingtime = MonthlyLegalWorkingTime.twentyEighthDay;
		}else if(maxDay == 29){
			tempMonthlyLegalWorkingtime = MonthlyLegalWorkingTime.twentyNinthDay;
		}else if(maxDay == 30){
			tempMonthlyLegalWorkingtime = MonthlyLegalWorkingTime.thirtiethDay;
		}else if(maxDay == 31){
			tempMonthlyLegalWorkingtime = MonthlyLegalWorkingTime.thirtyFirstDay;
		}
		String monthlyLegalWorkingtime = getTimeDoubleToString(tempMonthlyLegalWorkingtime);

		return monthlyLegalWorkingtime;
	}

	/*従業員種別ごとの日の基準労働時間取得*/
	public static String getStandardsWorkingTimeFromWorkingtype(Integer workingytpeId){

		double standardWorkingTime = 0;

		if(workingytpeId == 1){
			standardWorkingTime = ConstantWorkingTime.WORKINGTIME;
		}else if(workingytpeId == 2){
			standardWorkingTime = ConstantWorkingTime.IRREGULARWORKINGTIME;
		}else if(workingytpeId == 3){
			standardWorkingTime = ConstantWorkingTime.FREXWORKINGTIME;
		}
		String workingTime = getTimeDoubleToString(standardWorkingTime);
		return workingTime;
	}
}
