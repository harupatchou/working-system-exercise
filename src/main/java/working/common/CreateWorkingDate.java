package main.java.working.common;

import java.text.SimpleDateFormat;

import main.java.working.entity.WorkingDay;

/**
 *
 * 時間設定を行うクラス
 * @author kuro
 *
 */
public class CreateWorkingDate {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	//時間設定時初期値
	String initialTime = "0:00";
	//出勤時間
	String attendanceTime;
	//退勤時間
	String leaveTime;
	//休憩開始
	String breakStartTime;
	//休憩終了
	String breakEndTime;

	public CreateWorkingDate(WorkingDay workingDay){
		this.attendanceTime = workingDay.getAttendanceTime();
		this.leaveTime = workingDay.getLeaveTime();
		this.breakStartTime = workingDay.getBreakTimeStart();
		this.breakEndTime = workingDay.getBreakTimeEnd();
	}

/** gettersetter */
	public String getInitialTime() {
		return initialTime;
	}

	public void setInitialTime(String initialTime) {
		this.initialTime = initialTime;
	}
/** gettersetter */

}
