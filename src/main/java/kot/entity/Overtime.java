package main.java.kot.entity;

public class Overtime {

	/*残業ID*/
	private Integer overtimeId;
	/*法定内残業*/
	private String legalOvertime;
	/*法定外残業*/
	private String statutoryOvertime;
	/*深夜残業*/
	private String nightOvertime;
	/*日付ID*/
	private Integer dailyId;

	public Overtime(){
	}

	public Overtime(Integer overtimeId,String legalOvertime,String statutoryOvertime,String nightOvertime,Integer dailyId){
		this.overtimeId = overtimeId;
		this.legalOvertime = legalOvertime;
		this.statutoryOvertime = statutoryOvertime;
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
	public String getStatutoryOvertime() {
		return statutoryOvertime;
	}
	public void setStatutoryOvertime(String statutoryOvertime) {
		this.statutoryOvertime = statutoryOvertime;
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
