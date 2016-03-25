package main.java.kot.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import main.java.kot.common.Schedule;

public class CalendarUtil {

	//曜日定数
	public static final Integer SUNDAY = 1;
	public static final Integer MONDAY = 2;
	public static final Integer TUESDAY = 3;
	public static final Integer WEDNESDAY = 4;
	public static final Integer THURSDAY = 5;
	public static final Integer FRIDAY = 6;
	public static final Integer SATURDAY = 7;

	//年月日から曜日取得
	public static Schedule getWeek(Integer year,Integer month,Integer day){

		//TODO 現状holidayFlagは固定 いずれ設定できるように

		GregorianCalendar calendar = new GregorianCalendar(year, month-1, day);
		Schedule weekInfo = new Schedule();

		switch (calendar.get(Calendar.DAY_OF_WEEK)){
		// 取得した曜日フィールドの値と各曜日を表す値を比較して曜日を得ます。
		case Calendar.SUNDAY:
			weekInfo.setWeekNum(1);
			weekInfo.setWeekStr("日");
			weekInfo.setHolidayFlag(1);
			break;
		case Calendar.MONDAY:
			weekInfo.setWeekNum(2);
			weekInfo.setWeekStr("月");
			weekInfo.setHolidayFlag(0);
			break;
		case Calendar.TUESDAY:
			weekInfo.setWeekNum(3);
			weekInfo.setWeekStr("火");
			weekInfo.setHolidayFlag(0);
			break;
		case Calendar.WEDNESDAY:
			weekInfo.setWeekNum(4);
			weekInfo.setWeekStr("水");
			weekInfo.setHolidayFlag(0);
			break;
		case Calendar.THURSDAY:
			weekInfo.setWeekNum(5);
			weekInfo.setWeekStr("木");
			weekInfo.setHolidayFlag(0);
			break;
		case Calendar.FRIDAY:
			weekInfo.setWeekNum(6);
			weekInfo.setWeekStr("金");
			weekInfo.setHolidayFlag(0);
			break;
		case Calendar.SATURDAY:
			weekInfo.setWeekNum(7);
			weekInfo.setWeekStr("土");
			weekInfo.setHolidayFlag(1);
			break;
		}


		return weekInfo;
	}

}
