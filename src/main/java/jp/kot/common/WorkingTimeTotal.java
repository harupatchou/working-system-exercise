package main.java.jp.kot.common;

/**
 * 総労働・残業時間
 * @author ueno
 **/
public class WorkingTimeTotal {
	//総労働時間
	private String workingTimeTotal;
	//総残業時間
	private String overWorkingTimeTotal;
	//深夜労働時間
	private String nightTimeTotal;
	//深夜残業時間
	private String overNightTimeTotal;

	public WorkingTimeTotal(){
	}

	public WorkingTimeTotal(String workingTimeTotal, String overWorkingTimeTotal,String nightTimeTotal,String overNightTimeTotal ){
		this.workingTimeTotal = workingTimeTotal;
		this.overWorkingTimeTotal = overWorkingTimeTotal;
		this.nightTimeTotal = nightTimeTotal;
		this.overNightTimeTotal = overNightTimeTotal;
	}

	public String getWorkingTimeTotal() {
		return workingTimeTotal;
	}
	public void setWorkingTimeTotal(String workingTimeAll) {
		this.workingTimeTotal = workingTimeAll;
	}
	public String getOverWorkingTimeTotal() {
		return overWorkingTimeTotal;
	}
	public void setOverWorkingTimeTotal(String overWorkingTimeAll) {
		this.overWorkingTimeTotal = overWorkingTimeAll;
	}
	public String getNightTimeTotal() {
		return nightTimeTotal;
	}
	public void setNightTimeTotal(String nightTimeTotal) {
		this.nightTimeTotal = nightTimeTotal;
	}
	public String getOverNightTimeTotal() {
		return overNightTimeTotal;
	}
	public void setOverNightTimeTotal(String overNightTimeTotal) {
		this.overNightTimeTotal = overNightTimeTotal;
	}
}