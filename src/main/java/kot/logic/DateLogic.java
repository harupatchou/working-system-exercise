package main.java.kot.logic;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import main.java.kot.common.Schedule;
import main.java.kot.employee.attendance.service.AttendanceServise;
import main.java.kot.entity.WorkingDay;
import main.java.kot.util.CalendarUtil;


public class DateLogic {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/* 入力した時間からhour、minutesを取得 */
	public static String[] timeStr (String str){
		String[] timeStr = str.split(":");
		return timeStr;
	}


	/* 送られてきた二つのString[]を比較し、String型00:00表記の時間を返す */
	public static String getStringTime (String[] startStr,String[] endStr){

			Integer startHour = Integer.parseInt(startStr[0]);
			Integer endHour = Integer.parseInt(endStr[0]);
			Integer startMinute = Integer.parseInt(startStr[1]);
			Integer endMinute = Integer.parseInt(endStr[1]);

			String strTime = timeCalculate(startHour,endHour,startMinute,endMinute);

			return strTime;
	}

	/* 比較部分切り出し */
	public static String timeCalculate (Integer startHour,Integer endHour,Integer startMinute,Integer endMinute){

		//計算用integer型変数
		Integer minuteTime = 60;
		Integer hourTime = 0;

		/* 分計算 */
		if((endMinute - startMinute)<0){
			hourTime -= 1;
			minuteTime += endMinute - startMinute;
		}else{
			minuteTime = endMinute - startMinute;
		}

		/* 時間計算 */
		if((endHour - startHour)<0){
			hourTime += 24 - startHour;
		}else{
			hourTime += endHour - startHour;
		}

		String strHourTime = String.valueOf(hourTime);
		String strMinuteTime = String.valueOf(minuteTime);
		if(strMinuteTime.length() == 1) strMinuteTime = "0" + strMinuteTime;

		String time = strHourTime + ":" + strMinuteTime;

		return time;
	}

	/* 実労働算出 */
	public static String getCalculateTime (String allAttendTime,String breakTime){

		String[] allAttendStr = timeStr(allAttendTime);
		String[] breakTimeStr = timeStr(breakTime);

		Integer attendHour = Integer.parseInt(allAttendStr[0]);
		Integer attendMinute = Integer.parseInt(allAttendStr[1]);
		Integer breakHour = Integer.parseInt(breakTimeStr[0]);
		Integer breakMinute = Integer.parseInt(breakTimeStr[1]);

		String strTime = realCalculate(attendHour,breakHour,attendMinute,breakMinute);

		return strTime;

	}

	/* 実労働計算 */
	public static String realCalculate (Integer attendHour,Integer breakHour,Integer attendMinute,Integer breakMinute){

		//計算用integer型変数
		Integer minuteTime = 60;
		Integer hourTime = 0;

		/* 分計算 */
		if((attendMinute - breakMinute)<0){
			hourTime -= 1;
			minuteTime += attendMinute - breakMinute;
		}else{
			minuteTime = attendMinute - breakMinute;
		}

		/* 時間計算 */
		hourTime += attendHour - breakHour;

		String strHourTime = String.valueOf(hourTime);
		String strMinuteTime = String.valueOf(minuteTime);
		if(strMinuteTime.length() == 1) strMinuteTime = "0" + strMinuteTime;

		String time = strHourTime + ":" + strMinuteTime;

		return time;
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

	//画面に送信する情報をListに格納していく
	//TODO 出退勤時間等も入れていく.
	public static List<Schedule> getMonthlyDate (Integer year,Integer month,Integer day_count, Integer userId){

		//画面表示用に月の情報を格納するList
		List<Schedule> scheduleList = new ArrayList<Schedule>();

		//年月を固定なのでStringの変数に代入
		String strYear = String.valueOf(year);
		String strMonth =  String.valueOf(month);

		for (int i = 0;i < day_count; i++){
			List<String> addDate = new ArrayList<String>();
			Schedule tempSchedule = new Schedule();
			WorkingDay tempWork = new WorkingDay();

			addDate.add(strYear);
			addDate.add(strMonth);
			addDate.add(String.valueOf(i+1));

			Schedule weekInfo = CalendarUtil.getWeek(year,month,i+1);

			tempSchedule.setMonthlyDate(GeneralLogic.joinString(addDate, "/"));
			tempSchedule.setWeekStr(weekInfo.getWeekStr());
			tempSchedule.setWeekNum(weekInfo.getWeekNum());
			tempSchedule.setHolidayFlag(weekInfo.getHolidayFlag());
			if(tempSchedule.getHolidayFlag() == 0){
				tempSchedule.setStrHoliday("通常");
			}else{
				tempSchedule.setStrHoliday("休日");
			}

			tempWork = AttendanceServise.selectAllByEmployeeId(year, month,i+1, userId);

			if(tempWork.getId() == null){
				tempSchedule.setEnterStatus("未入力");
			}else{
				tempSchedule.setEnterStatus("入力済");
			}

			tempSchedule.setWorkingDay(tempWork);

			scheduleList.add(tempSchedule);
		}
		return scheduleList;

	}

	//0000/0/0形式のDateを0000/00/00の形式に変換
	public static String formatDate (String oldDate){

		String[] arrayDate = oldDate.split("/");
		String formatDate = arrayDate[0];

		for (int i=0;i<arrayDate.length-1;i++){
			if(arrayDate[i+1].length() == 1){
				formatDate += "/0" + arrayDate[i+1];
			} else {
				formatDate += "/" + arrayDate[i+1];
			}
		}
		return formatDate;
	}

	//0:00形式のStringを00:00の形式に変換
	public static String formatTime (String oldTime){

		String[] arrayTime = oldTime.split(":");
		String formatTime ="";

		if(arrayTime[0].length() == 1){
			formatTime += "0" + arrayTime[0] + ":";
		} else {
			formatTime += arrayTime[0] + ":";
		}

		if(arrayTime[1].length() == 1){
			formatTime += "0" + arrayTime[1];
		} else {
			formatTime += arrayTime[1];
		}

		return formatTime;
	}
}
