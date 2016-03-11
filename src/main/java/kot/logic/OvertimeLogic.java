package main.java.kot.logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import main.java.kot.common.CalculationWorkingTimeTotal;
import main.java.kot.common.TempTime;
import main.java.kot.common.workingtime.constant.ConstantWorkingTime;
import main.java.kot.common.workingtime.constant.MonthlyLegalWorkingTime;
import main.java.kot.common.workingtime.constant.WeeklyLegalWorkingTime;
import main.java.kot.dao.WorkingTimeDao;
import main.java.kot.entity.Overtime;
import main.java.kot.entity.WorkingDay;

/**
 * 残業関連ロジッククラス
 * @author ueno
 **/
public class OvertimeLogic {


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

	/*実労働時間と法定労働時間の時刻の差を取得*/
	public static String subtractionWorkingTime(String workingTime , String legalWorkingTime){

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

	/*実労働時間を法定外残業にセット(土日用処理)*/
	public static Overtime getOvertimeOnSaturdayAndSunday(WorkingDay workingday){

		Overtime overtime = new Overtime();

		String[] startTimeStr = DateLogic.timeStr(workingday.getAttendanceTime());
		String[] endTimeStr = DateLogic.timeStr(workingday.getLeaveTime());

		//一日の総労働時間算出
		String attendDayAll = DateLogic.getStringTime(startTimeStr,endTimeStr);

		//一日の実労働時間算出
		String attendDay= DateLogic.getCalculateTime(attendDayAll,workingday.getNapTime());

		overtime.setLegalOvertime("0:00");
		overtime.setStatutoryOvertime(attendDay);
		overtime.setNightOvertime("0:00");
		overtime.setStatutoryNightOvertime("0:00");

		return overtime;
	}

	/*通常残業時間*/
	public static Overtime getOvertime(WorkingDay workingday){

		/*dailyIDはDBインサート直前でセット*/
		Overtime overtime = new Overtime();

		//土日用処理
		if(workingday.getWeek() == 7 || workingday.getWeek() == 1){
			overtime = getOvertimeOnSaturdayAndSunday(workingday);
			return overtime;
		}

		//対象従業員種別の終業時刻
		String workingtypeEndtime = DataLogic.getAttendanceTimeFromEmployeeId(workingday.getEmployeeId()).getEnd_time();
		TempTime startOvertime = getTimeInt(workingtypeEndtime);

		//退勤時刻
		TempTime endOvertime = getTimeInt(workingday.getLeaveTime());

		String tempOvertime = getWorkingtimeLag(startOvertime, endOvertime);

		overtime.setLegalOvertime("0:00");
		overtime.setStatutoryOvertime(tempOvertime);
		overtime.setNightOvertime("0:00");
		overtime.setStatutoryNightOvertime("0:00");
		return overtime;
	}


	/*変形労働制残業時間(一ヶ月単位)*/
	public static Overtime getIrregularWorkingHourSystemOvertime(WorkingDay workingday){
		//残業情報格納用(dailyIDはDBインサート直前でセット)
		Overtime overtime = new Overtime();

		//土日用処理
		if(workingday.getWeek() == 7 || workingday.getWeek() == 1){
			overtime = getOvertimeOnSaturdayAndSunday(workingday);
			return overtime;
		}

		//対象従業員種別の終業時刻
		String workingtypeEndtime = DataLogic.getAttendanceTimeFromEmployeeId(workingday.getEmployeeId()).getEnd_time();
		TempTime startOvertime = getTimeInt(workingtypeEndtime);

		//退勤時刻
		TempTime endOvertime = getTimeInt(workingday.getLeaveTime());

		//残業時間
		String totalOvertime = getWorkingtimeLag(startOvertime, endOvertime);


				/*月の残業計算 ここから*/

		// TODO 特例措置企業については考慮していない
		//現時点での今月の総労働時間
		CalculationWorkingTimeTotal currentCalculationWorkingTimeTotal = WorkingTimeDao.getCurrentWorkingTimeTotal(workingday.getEmployeeId(), workingday.getDate());
		String currentWorkingTimeTotal = currentCalculationWorkingTimeTotal.getWorkingTimeTotal();

		//月の最大日数から月の法定労働時間をセット
		String monthlyLegalWorkingtime = getMonthlyLegalWorkingtime(workingday.getDate());

		//実労働時間が法定労働時間を上回る場合、法定外残業にセットしてリターン
		String largeMonthlyWorkingTime = compareWorkingTime(currentWorkingTimeTotal, monthlyLegalWorkingtime);
		if(largeMonthlyWorkingTime.equals(currentWorkingTimeTotal)){
			String monthlyOvetime = subtractionWorkingTime(currentWorkingTimeTotal, monthlyLegalWorkingtime);

			overtime.setLegalOvertime("0:00");
			overtime.setStatutoryOvertime(monthlyOvetime);
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
			return overtime;
		}

				/*月の残業計算 ここまで*/

				/*週の残業計算 ここから*/

		Calendar cal = Calendar.getInstance();
		cal.setTime(workingday.getDate());
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		int startDay = 0;
		int endDay = day;

		//金曜日に週の労働時間と法定労働時間から残業計算を行う
		if(workingday.getWeek() == 6){
			//第一週
			if(day <= 5){
				startDay = 1;
			}else if(day > 5 && day < maxDay){
				startDay = day - 6;
			}
		//金曜日以外の場合
		}else if(workingday.getWeek() != 6){
			startDay = day - workingday.getWeek() + 2;//土曜から今日までの期間(曜日が1～7までのint型で判定なので金曜日との差を出すために+2)
		}

		//今週の実労働時間
		CalculationWorkingTimeTotal currentCalculationWeeklyWorkingTimeTotal = WorkingTimeDao.getCurrentWeeklyWorkingTimeTotal(workingday, startDay, endDay);
		String currentWeeklyWorkingTimeTotal = currentCalculationWeeklyWorkingTimeTotal.getWorkingTimeTotal();
		//週の法定労働時間
		String weeklyLegalWorkingTime = getTimeDoubleToString(WeeklyLegalWorkingTime.weeklyLegalWorkingTime);
		String largeWeeklyWorkingTime = compareWorkingTime(currentWeeklyWorkingTimeTotal, weeklyLegalWorkingTime);

		if(largeWeeklyWorkingTime.equals(currentWeeklyWorkingTimeTotal)){
			String weeklyOvertime = subtractionWorkingTime(currentWeeklyWorkingTimeTotal,weeklyLegalWorkingTime);
			overtime.setLegalOvertime("0:00");
			overtime.setStatutoryOvertime(weeklyOvertime);
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
			return overtime;
		}

				/*週の残業計算 ここまで*/

				/*日の残業計算 ここから*/

		//法定労働時間と所定労働時間の差
		double timeLag = ConstantWorkingTime.WORKINGTIME - ConstantWorkingTime.IRREGULARWORKINGTIME;
		String timeLagStr = getTimeDoubleToString(timeLag);

		//法定内残業・法定外残業セット
		TempTime tempTotalOverTime = getTimeInt(totalOvertime);
		TempTime tempStatutoryOvertime = getTimeInt(timeLagStr);
		//法定内で収まらない場合
		if(tempTotalOverTime.getHour() > tempStatutoryOvertime.getHour() ||
				(tempTotalOverTime.getHour() == tempStatutoryOvertime.getHour() &&
				tempTotalOverTime.getMinute() > tempStatutoryOvertime.getMinute())){
			String statutoryOvertime = getWorkingtimeLag(getTimeInt(timeLagStr),getTimeInt(totalOvertime));
			overtime.setLegalOvertime(timeLagStr);
			overtime.setStatutoryOvertime(statutoryOvertime);
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
		//法定内で収まる場合
		}else if(tempTotalOverTime.getHour() == tempStatutoryOvertime.getHour() &&
				tempTotalOverTime.getMinute() > 0 &&
				tempTotalOverTime.getMinute() < tempStatutoryOvertime.getMinute()){
			overtime.setLegalOvertime(totalOvertime);
			overtime.setStatutoryOvertime("0:00");
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
		}

				/*日の残業計算 ここまで*/

		return overtime;
	}



	/* TODO 早出残業用メソッド(現在は考慮しないで良い) */
	public static String getEarlyAttendanceOvertime(){
		return null;
	}
}
