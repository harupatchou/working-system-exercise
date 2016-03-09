package main.java.kot.common.workingtime.constant;

/**
 * 所定労働時間用
 * @author ueno
 **/
public class RegularWorkingTime {

	/*所定労働時間(所定時間を任意で定められる場合使用)*/
	private double regularWorkingTime;
	/*週の所定労働時間*/
	private double weeklyRegularWorkingTime;
	/*月の所定労働時間*/
	private double monthlyRegularWorkingTime;

	public RegularWorkingTime(){
	}

	public RegularWorkingTime(double regularWorkingTime, double weeklyRegularWorkingTime, double monthlyRegularWorkingTime){
		this.regularWorkingTime = regularWorkingTime;
		this.weeklyRegularWorkingTime = weeklyRegularWorkingTime;
		this.monthlyRegularWorkingTime = monthlyRegularWorkingTime;
	}

	public double getRegularWorkingTime() {
		return regularWorkingTime;
	}
	public void setRegularWorkingTime(double regularWorkingTime) {
		this.regularWorkingTime = regularWorkingTime;
	}
	public double getWeeklyRegularWorkingTime() {
		return weeklyRegularWorkingTime;
	}
	public void setWeeklyRegularWorkingTime(double weeklyRegularWorkingTime) {
		this.weeklyRegularWorkingTime = weeklyRegularWorkingTime;
	}
	public double getMonthlyRegularWorkingTime() {
		return monthlyRegularWorkingTime;
	}
	public void setMonthlyRegularWorkingTime(double monthlyRegularWorkingTime) {
		this.monthlyRegularWorkingTime = monthlyRegularWorkingTime;
	}

}
