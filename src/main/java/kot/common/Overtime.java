package main.java.kot.common;

public class Overtime {

	/*残業ID*/
	private Integer overtimeId;
	/*法定内残業*/
	private String legalOvertime;
	/*法定外残業*/
	private String nonLeagalOvertime;
	/*日付ID*/
	private Integer dailyId;

	public Overtime(){
	}

	public Overtime(Integer overtimeId,String legalOvertime,String nonLeagalOvertime,Integer dailyId){
		this.overtimeId = overtimeId;
		this.legalOvertime = legalOvertime;
		this.nonLeagalOvertime = nonLeagalOvertime;
		this.dailyId = dailyId;
	}

	public Integer getOvertimeId() {
		return overtimeId;
	}
	public void setOvertimeId(Integer overtimeId) {
		this.overtimeId = overtimeId;
	}
	public String getLegalOvertime() {
		return legalOvertime;
	}
	public void setLegalOvertime(String legalOvertime) {
		this.legalOvertime = legalOvertime;
	}
	public String getNonLeagalOvertime() {
		return nonLeagalOvertime;
	}
	public void setNonLeagalOvertime(String nonLeagalOvertime) {
		this.nonLeagalOvertime = nonLeagalOvertime;
	}
	public Integer getDailyId() {
		return dailyId;
	}
	public void setDailyId(Integer dailyId) {
		this.dailyId = dailyId;
	}

}
