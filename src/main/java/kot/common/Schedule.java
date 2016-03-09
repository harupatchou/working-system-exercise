package main.java.kot.common;

public class Schedule {

	//週のintの値
	Integer weekNum;
	//週のStringでの値
	String weekStr;
	//休日フラグ
	Integer holidayFlag;


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

}
