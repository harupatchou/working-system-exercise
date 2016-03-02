package main.java.jp.kot.common;

public class WorkingTimeAll {
	//総労働時間
	private Integer workingTimeAll;
	//総残業時間
	private Integer overWorkingTimeAll;

	public WorkingTimeAll(){
	}

	public WorkingTimeAll(Integer workingTimeAll, Integer overWorkingTimeAll){
		this.workingTimeAll = workingTimeAll;
		this.overWorkingTimeAll = overWorkingTimeAll;
	}

	public Integer getWorkingTimeAll() {
		return workingTimeAll;
	}
	public void setWorkingTimeAll(Integer workingTimeAll) {
		this.workingTimeAll = workingTimeAll;
	}
	public Integer getOverWorkingTimeAll() {
		return overWorkingTimeAll;
	}
	public void setOverWorkingTimeAll(Integer overWorkingTimeAll) {
		this.overWorkingTimeAll = overWorkingTimeAll;
	}
}
