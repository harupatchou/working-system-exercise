package main.java.kot.entity;

import java.util.Date;

public class WorkingDay {

	//id
	private Integer id;
	//日付
	private Date date;
	//曜日
	private Integer week;
	//出社時間
	private String attendanceTime;
	//退社時間
	private String leaveTime;
	//休憩開始時間
	private String breakTimeStart;
	//休憩終了時間
	private String breakTimeEnd;
	//仮眠時間
	private String napTime;
	//従業員id
	private Integer employeeId;
	//所定フラグ
	/**
	 * 0 通常出勤
	 * 1 休日出勤
	 **/
	private Integer legalFlag;
	//出勤常態
	private String attendanceStatus;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public String getAttendanceTime() {
		return attendanceTime;
	}
	public void setAttendanceTime(String attendanceTime) {
		this.attendanceTime = attendanceTime;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getBreakTimeStart() {
		return breakTimeStart;
	}
	public void setBreakTimeStart(String breakTimeStart) {
		this.breakTimeStart = breakTimeStart;
	}
	public String getBreakTimeEnd() {
		return breakTimeEnd;
	}
	public void setBreakTimeEnd(String breakTimeEnd) {
		this.breakTimeEnd = breakTimeEnd;
	}
	public String getNapTime() {
		return napTime;
	}
	public void setNapTime(String napTime) {
		this.napTime = napTime;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public Integer getLegalFlag() {
		return legalFlag;
	}
	public void setLegalFlag(int i) {
		this.legalFlag = i;
	}
	public String getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	public void setLegalFlag(Integer legalFlag) {
		this.legalFlag = legalFlag;
	}



}
