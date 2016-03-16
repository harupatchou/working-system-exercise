package main.java.kot.common;

import main.java.kot.entity.WorkingDay;


public class Schedule {

	//週のintの値
	Integer weekNum;
	//週のStringでの値
	String weekStr;
	//休日フラグ
	Integer holidayFlag;
	//休日文字列
	String strHoliday;
	//未入力ステータス
	String enterStatus;
	//休憩時間
	String breakTime;
	//working_day
	WorkingDay workingDay;
	//年月日
	//TODO Dateの方がいい？
	String monthlyDate;

	public Integer getWeekNum() {
		return weekNum;
	}
	public void setWeekNum(Integer weekNum) {
		this.weekNum = weekNum;
	}
	public String getWeekStr() {
		return weekStr;
	}
	public void setWeekStr(String weekStr) {
		this.weekStr = weekStr;
	}
	public Integer getHolidayFlag() {
		return holidayFlag;
	}
	public void setHolidayFlag(Integer holidayFlag) {
		this.holidayFlag = holidayFlag;
	}
	public String getMonthlyDate() {
		return monthlyDate;
	}
	public void setMonthlyDate(String monthlyDate) {
		this.monthlyDate = monthlyDate;
	}
	public String getStrHoliday() {
		return strHoliday;
	}
	public void setStrHoliday(String strHoliday) {
		this.strHoliday = strHoliday;
	}
	public WorkingDay getWorkingDay() {
		return workingDay;
	}
	public void setWorkingDay(WorkingDay workingDay) {
		this.workingDay = workingDay;
	}
	public String getEnterStatus() {
		return enterStatus;
	}
	public void setEnterStatus(String enterStatus) {
		this.enterStatus = enterStatus;
	}
	public String getBreakTime() {
		return breakTime;
	}
	public void setBreakTime(String breakTime) {
		this.breakTime = breakTime;
	}

}
