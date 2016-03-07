package main.java.kot.common;

public class WorkingTime {

	//実働時間
	private Integer workTime;
	//残業時間
	private Integer overWorkTime;

	public WorkingTime(){
	}

	public WorkingTime(Integer workTime, Integer overWorkTime){
		this.workTime = workTime;
		this.overWorkTime = overWorkTime;
	}

	public Integer getWorkTime() {
		return workTime;
	}

	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}

	public Integer getOverWorkTime() {
		return overWorkTime;
	}

	public void setOverWorkTime(Integer overWorkTime) {
		this.overWorkTime = overWorkTime;
	}



}
