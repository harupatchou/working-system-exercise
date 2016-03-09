package main.java.kot.logic;

import java.sql.Date;
import java.util.Calendar;

import main.java.kot.common.AttendanceTime;


public class DateLogic {

	/* 入力した時間からhour、minutesを取得 */
	public static String[] timeStr (String str){
		String[] timeStr = str.split(":");
		return timeStr;
	}

	/* 送られてきた二つのString[]を比較し、開始から終了までの時間を返す */
	public static String attend (String[] startStr,String[] endStr,Integer attendFlag){

		if(attendFlag == 0){
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
		}else{
			Integer startHour = Integer.parseInt(startStr[0]);
			Integer endHour = Integer.parseInt(endStr[0]);
			Integer startMinute = Integer.parseInt(startStr[1]);
			Integer endMinute = Integer.parseInt(endStr[1]);

			//計算用integer型変数
			Integer minuteTime = 60;
			Integer hourTime = 0;

			/* 分数計算 */
			if((endMinute-startMinute)<0){
				hourTime -= 1;
				minuteTime += endMinute-startMinute;
			}else{
				minuteTime = endMinute-startMinute;
			}

			/* 時間計算 */
			if((endHour-startHour)<0){
				hourTime += 24-startHour;
			}else{
				hourTime += endHour-startHour;
			}

			String strHourTime = String.valueOf(hourTime);
			String strMinuteTime = String.valueOf(minuteTime);
			if(strMinuteTime.length() == 1) strMinuteTime = "0" + strMinuteTime;

			String breakTime = strHourTime + ":" + strMinuteTime;

			return breakTime;
		}
	}

	/* Dataの変換 util→sql */
	public static Date sqlDate (Date utilDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(utilDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());

		return sqlDate;
	}
}
