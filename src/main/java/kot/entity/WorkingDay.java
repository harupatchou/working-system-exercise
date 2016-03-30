package main.java.kot.entity;

import java.util.Calendar;
import java.util.Date;

import main.java.kot.common.StrTime;

public class WorkingDay {
	//所定フラグ
	/**
	 * 0 通常出勤
	 * 1 休日出勤
	 **/
	public final static Integer WEEKDAY_WORK = 0;
	public final static Integer HOLIDAY_WORK = 1;
	//ステータスコード
	/**
	 * 1 通常出勤
	 * 2 欠勤
	 * 3 有給
	 * 4 未入力
	 **/
<<<<<<< HEAD
	public final static int NORMAL_ATTENDANCE = 1;
	public final static int ABSENCE = 2;
	public final static int SALARIED = 3;
	public final static int NO_INPUT = 4;

=======
	public final static Integer NORMAL_ATTENDANCE = 1;
	public final static Integer ABSENCE = 2;
>>>>>>> 0ee03a8edae21ac000c78f821a47272a39196978


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
	private Integer legalFlag;
	//出勤常態
	private Integer statusCode;

	private AttendanceStatus attendanceStatus;

	public WorkingDay(){
		Calendar cal = Calendar.getInstance();

		this.date = new Date();
		this.week = cal.get(Calendar.DAY_OF_WEEK);
		this.attendanceTime = StrTime.ZERO_HOUR;
		this.leaveTime = StrTime.ZERO_HOUR;
		this.breakTimeStart = StrTime.ZERO_HOUR;
		this.breakTimeEnd = StrTime.ZERO_HOUR;
		this.napTime = StrTime.ZERO_HOUR;
		this.statusCode = NO_INPUT;
	}

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
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public void setLegalFlag(Integer legalFlag) {
		this.legalFlag = legalFlag;
	}
	public AttendanceStatus getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}



}
