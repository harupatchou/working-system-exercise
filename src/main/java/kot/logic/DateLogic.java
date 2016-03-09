package main.java.kot.logic;

import main.java.kot.common.AttendanceTime;


public class DateLogic {

	/* 入力した時間からhour、minutesを取得 */
	public static String[] timeStr (String str){
		String[] timeStr = str.split(":");
		return timeStr;
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
