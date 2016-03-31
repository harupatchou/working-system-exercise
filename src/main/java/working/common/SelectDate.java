package main.java.working.common;

import java.util.List;

/**
 * DBに格納されているデータの年月を格納用
 **/
public class SelectDate {

	private List<Integer> yearList;
	private List<Integer> monthList;

	public SelectDate() {
	}

	public SelectDate(List<Integer> yearList, List<Integer> monthList) {
		super();
		this.yearList = yearList;
		this.monthList = monthList;
	}

	public List<Integer> getYearList() {
		return yearList;
	}
	public void setYearList(List<Integer> yearList) {
		this.yearList = yearList;
	}
	public List<Integer> getMonthList() {
		return monthList;
	}
	public void setMonthList(List<Integer> monthList) {
		this.monthList = monthList;
	}

}
