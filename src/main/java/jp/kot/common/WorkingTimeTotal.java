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

	public WorkingTimeTotal(){
	}

	public WorkingTimeTotal(String workingTimeAll, String overWorkingTimeAll){
		this.workingTimeTotal = workingTimeAll;
		this.overWorkingTimeTotal = overWorkingTimeAll;
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
}