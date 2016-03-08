package main.java.kot.logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import main.java.kot.common.AttendanceTime;
import main.java.kot.common.TempTime;
import main.java.kot.common.WorkingDay;


public class DateLogic {

	/* 入力した時間からhour、minutesを取得 */
	public static String[] timeStr (String str){
		String[] timeStr = str.split(":");
		return timeStr;
	}

	/*時分を格納したstring型配列からint型の時分オブジェクトを取得*/
	public static TempTime getTimeInt(String[] strArray){
		TempTime temptime = new TempTime();
		temptime.setHour(Integer.parseInt(strArray[0]));
		temptime.setMinute(Integer.parseInt(strArray[1]));
		return temptime;
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
		String workingTime = workingTimeArray[0] + ":" + workingTimeArray[1];

		return workingTime;
	}

	/*実労働時間算出(現状通常労働時間制のみ、他は随時追加。また、早出・遅刻などは後ほど追加)*/
	public static String getWorkingTime(WorkingDay workingday){

		//対象従業員種別の始業時刻
		String workingtypeStartTime = GetDataLogic.getAttendanceTimeFromEmployeeId(workingday.getEmployeeId()).getStart_time();
		TempTime tempWorkingtypeStartTime = getTimeInt(timeStr(workingtypeStartTime));

		//退勤時刻
		TempTime endtime = getTimeInt(timeStr(workingday.getLeaveTime()));

		return getWorkingtimeLag(tempWorkingtypeStartTime, endtime);
	}


	/* 送られてきた二つのString[]を比較し、開始から終了までの時間を返す */
	public static String attend (String[] startStr,String[] endStr){

		AttendanceTime at = new AttendanceTime();

		Integer startHour = Integer.parseInt(startStr[0]);
		Integer endHour = Integer.parseInt(endStr[0]);
		Integer startMinute = Integer.parseInt(startStr[1]);
		Integer endMinute = Integer.parseInt(endStr[1]);

		String attendDay = "";

		//計算用integer型変数
		Integer minuteData = 0;
		Integer hourData = 0;

		/* 分数計算 */
		if((endMinute-startMinute)<0){
			hourData -= 1;
		}

		return null;
	}



}
