package main.java.kot.common;


public class StrTime {
	//出勤時間
	String startTime;
	//退社時間
	String endTime;
	//休憩開始時間
	String breakStartTime;
	//休憩終了時間
	String breakEndTime;
	//計算用配列出勤時間
	String[] arrayStartTime;
	//計算用配列退勤時間
	String[] arrayEndTime;
	//計算用配列休憩開始時間
	String[] arrayBreakStartTime;
	//計算用配列休憩終了時間
	String[] arrayBreakEndTime;
	//休憩時間合計
	String breakTime;
	//遅刻時間
	String lateTime;

	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getBreakStartTime() {
		return breakStartTime;
	}
	public void setBreakStartTime(String breakStartTime) {
		this.breakStartTime = breakStartTime;
	}
	public String getBreakEndTime() {
		return breakEndTime;
	}
	public void setBreakEndTime(String breakEndTime) {
		this.breakEndTime = breakEndTime;
	}
	public String[] getArrayStartTime() {
		return arrayStartTime;
	}
	public void setArrayStartTime(String[] arrayStartTime) {
		this.arrayStartTime = arrayStartTime;
	}
	public String[] getArrayEndTime() {
		return arrayEndTime;
	}
	public void setArrayEndTime(String[] arrayEndTime) {
		this.arrayEndTime = arrayEndTime;
	}
	public String[] getArrayBreakStartTime() {
		return arrayBreakStartTime;
	}
	public void setArrayBreakStartTime(String[] arrayBreakStartTime) {
		this.arrayBreakStartTime = arrayBreakStartTime;
	}
	public String[] getArrayBreakEndTime() {
		return arrayBreakEndTime;
	}
	public void setArrayBreakEndTime(String[] arrayBreakEndTime) {
		this.arrayBreakEndTime = arrayBreakEndTime;
	}
	public String getBreakTime() {
		return breakTime;
	}
	public void setBreakTime(String breakTime) {
		this.breakTime = breakTime;
	}
	public String getLateTime() {
		return lateTime;
	}
	public void setLateTime(String lateTime) {
		this.lateTime = lateTime;
	}
}
