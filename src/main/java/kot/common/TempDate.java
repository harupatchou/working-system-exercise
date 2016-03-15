package main.java.kot.common;

/**
 * 年月格納一時ファイル
 **/
public class TempDate {

	private int year;
	private int month;

	public TempDate() {
	}
	public TempDate(int year, int month) {
		super();
		this.year = year;
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}

}
