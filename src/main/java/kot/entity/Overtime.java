package main.java.kot.entity;

public class Overtime {

	/*残業ID*/
	private Integer overtimeId;
	/*法定内残業*/
	private String legalOvertime;
	/*法定外残業*/
	private String statutoryLeagalOvertime;
	/*深夜残業*/
	private String nightOvertime;
	/*日付ID*/
	private Integer dailyId;

	public Overtime(){
	}

	public Overtime(Integer overtimeId,String legalOvertime,String statutoryLeagalOvertime,String nightOvertime,Integer dailyId){
		this.overtimeId = overtimeId;
		this.legalOvertime = legalOvertime;
		this.statutoryLeagalOvertime = statutoryLeagalOvertime;
		this.nightOvertime = nightOvertime;
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
	public String getStatutoryLeagalOvertime() {
		return statutoryLeagalOvertime;
	}
	public void setStatutoryLeagalOvertime(String statutoryLeagalOvertime) {
		this.statutoryLeagalOvertime = statutoryLeagalOvertime;
	}
	public String getNightOvertime() {
		return nightOvertime;
	}
	public void setNightOvertime(String nightOvertime) {
		this.nightOvertime = nightOvertime;
	}
	public Integer getDailyId() {
		return dailyId;
	}
	public void setDailyId(Integer dailyId) {
		this.dailyId = dailyId;
	}

}
