package main.java.kot.common;

/**
 * String[]からint型の時分に変換する際使用、時刻一時ファイル
 * @author ueno
 **/
public class TempTime {

	private int hour;
	private int minute;

	public TempTime(){
	}

	public TempTime(int hour,int minute){
		this.hour = hour;
		this.minute = minute;
	}

	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
}
