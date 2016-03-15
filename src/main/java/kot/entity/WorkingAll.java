package main.java.kot.entity;

import java.util.Date;

public class WorkingAll {

	//id
	private Integer id;
	//日付
	private Date date;
	//曜日
	private Integer week;
	//実労働時間
	private String workingTimeAll;
	//所定内残業時間
	private String legalOvertimeAll;
	//所定外残業時間
	private String statutoryOverTimeAll;
	//深夜労働時間
	private String nightTimeAll;
	//深夜残業時間
	private String nightOvertimeAll;
	//遅刻時間
	private String lateTimeAll;
	//所定外休日時間
	private String dayStatus;
	//従業員id
	private Integer employeeId;


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
	public String getWorkingTimeAll() {
		return workingTimeAll;
	}
	public void setWorkingTimeAll(String workingTimeAll) {
		this.workingTimeAll = workingTimeAll;
	}
	public String getLegalOvertimeAll() {
		return legalOvertimeAll;
	}
	public void setLegalOvertimeAll(String legalOvertimeAll) {
		this.legalOvertimeAll = legalOvertimeAll;
	}
	public String getStatutoryOverTimeAll() {
		return statutoryOverTimeAll;
	}
	public void setStatutoryOverTimeAll(String statutoryOverTimeAll) {
		this.statutoryOverTimeAll = statutoryOverTimeAll;
	}
	public String getNightTimeAll() {
		return nightTimeAll;
	}
	public void setNightTimeAll(String nightTimeAll) {
		this.nightTimeAll = nightTimeAll;
	}
	public String getNightOvertimeAll() {
		return nightOvertimeAll;
	}
	public void setNightOvertimeAll(String nightOvertimeAll) {
		this.nightOvertimeAll = nightOvertimeAll;
	}
	public String getLateTimeAll() {
		return lateTimeAll;
	}
	public void setLateTimeAll(String lateTimeAll) {
		this.lateTimeAll = lateTimeAll;
	}
	public String getDayStatus() {
		return dayStatus;
	}
	public void setDayStatus(String dayStatus) {
		this.dayStatus = dayStatus;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

}
