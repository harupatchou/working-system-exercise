package main.java.kot.logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import main.java.kot.common.TempTime;
import main.java.kot.common.workingtime.constant.ConstantWorkingTime;
import main.java.kot.entity.Overtime;
import main.java.kot.entity.WorkingDay;

/**
 * 残業関連ロジッククラス
 * @author ueno
 **/
public class OvertimeLogic {

	/*時分を格納したstring型配列からint型の時分オブジェクトを取得*/
	public static TempTime getTimeInt(String[] strArray){
		TempTime temptime = new TempTime();
		temptime.setHour(Integer.parseInt(strArray[0]));
		temptime.setMinute(Integer.parseInt(strArray[1]));
		return temptime;
	}

	/*double型からString型の時分に変換(例.0.5→「0:30」)*/
	public static String getTimeDoubleToString(double time){
		int hour = (int)time;
		int minute;
		if(hour == 0){
			minute = (int)(60 * time);
		}else{
			minute = (int)(60 * (hour - time));
		}
		String timeString = String.valueOf(hour) + ":" + String.valueOf(minute);
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


	/*通常残業時間*/
	public static Overtime getOvertime(WorkingDay workingday){

		//対象従業員種別の終業時刻
		String workingtypeEndtime = DataLogic.getAttendanceTimeFromEmployeeId(workingday.getEmployeeId()).getEnd_time();
		TempTime startOvertime = getTimeInt(DateLogic.timeStr(workingtypeEndtime));

		//退勤時刻
		TempTime endOvertime = getTimeInt(DateLogic.timeStr(workingday.getLeaveTime()));

		String tempOvertime = getWorkingtimeLag(startOvertime, endOvertime);

		/* TODO dailyIDは後ほど入れる*/
		Overtime overtime = new Overtime();
		overtime.setLegalOvertime("0:00");
		overtime.setStatutoryLeagalOvertime(tempOvertime);
		return overtime;
	}


	//変形労働制残業時間(一ヶ月単位)
	public static Overtime getIrregularWorkingHourSystemOvertime(WorkingDay workingday){

		//対象従業員種別の終業時刻
		String workingtypeEndtime = DataLogic.getAttendanceTimeFromEmployeeId(workingday.getEmployeeId()).getEnd_time();
		TempTime startOvertime = getTimeInt(DateLogic.timeStr(workingtypeEndtime));

		//退勤時刻
		TempTime endOvertime = getTimeInt(DateLogic.timeStr(workingday.getLeaveTime()));

		//残業時間
		String tempOvertime = getWorkingtimeLag(startOvertime, endOvertime);

		//法定労働時間と所定労働時間の差
		double timeLag = ConstantWorkingTime.WORKINGTIME - ConstantWorkingTime.IRREGULARWORKINGTIME;
		String timeLagStr = getTimeDoubleToString(timeLag);






		return null;
	}



	/* TODO 早出残業用メソッド(現在は考慮しないで良い) */
	public static String getEarlyAttendanceOvertime(){
		return null;
	}
}
