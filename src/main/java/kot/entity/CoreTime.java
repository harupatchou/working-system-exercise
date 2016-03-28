package main.java.kot.entity;

public class CoreTime {

	//コアタイム開始時間
	private String coreTimeStrat;
	//コアタイム終了時間
	private String coreTimeEnd;

	public String getCoreTimeStrat() {
		return coreTimeStrat;
	}
	public void setCoreTimeStrat(String coreTimeStrat) {
		this.coreTimeStrat = coreTimeStrat;
	}
	public String getCoreTimeEnd() {
		return coreTimeEnd;
	}
	public void setCoreTimeEnd(String coreTimeEnd) {
		this.coreTimeEnd = coreTimeEnd;
	}


	public CoreTime() {
	}
	public CoreTime(String coreTimeStrat, String coreTimeEnd) {
		this.coreTimeStrat = coreTimeStrat;
		this.coreTimeEnd = coreTimeEnd;
	}

}
