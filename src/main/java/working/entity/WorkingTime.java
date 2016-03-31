package main.java.working.entity;

public class WorkingTime {
	/*ID*/
	private Integer workingTimeId;
	/*日の労働時間*/
	private double workingTime;
	/*持越し時間(フレックス制のみ、法定労働時間に達しなかった場合翌月に持越し)*/
	private String carryoverTime;
	/*労働制*/
	private LaborSystem laborSystem;

	public WorkingTime() {
	}

	public WorkingTime(Integer workingTimeId, double workingTime, String carryoverTime,
			LaborSystem laborSystem) {
		this.workingTimeId = workingTimeId;
		this.workingTime = workingTime;
		this.carryoverTime = carryoverTime;
		this.laborSystem = laborSystem;
	}

	public Integer getWorkingTimeId() {
		return workingTimeId;
	}
	public void setWorkingTimeId(Integer workingTimeId) {
		this.workingTimeId = workingTimeId;
	}
	public double getWorkingTime() {
		return workingTime;
	}
	public void setWorkingTime(double workingTime) {
		this.workingTime = workingTime;
	}
	public String getCarryoverTime() {
		return carryoverTime;
	}
	public void setCarryoverTime(String carryoverTime) {
		this.carryoverTime = carryoverTime;
	}
	public LaborSystem getLaborSystem() {
		return laborSystem;
	}
	public void setLaborSystem(LaborSystem laborSystem) {
		this.laborSystem = laborSystem;
	}




}
