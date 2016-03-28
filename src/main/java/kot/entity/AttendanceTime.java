package main.java.kot.entity;
/**
 * 勤怠時間等情報
 * @author kuro
 **/
public class AttendanceTime {

	//ID
	private Integer id;
	//出勤時間
	private String startTime;
	//退勤時間
	private String endTime;
/*	//コアタイム開始時間
	private String coreTimeStrat;
	//コアタイム終了時間
	private String coreTimeEnd;*/
	//コアタイム
	private CoreTime coreTime;
	//従業員種別
	private LaborSystem laborSystem;
	//カンパニー
	private Company company;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

/*	public String getCoreTimeStrat() {
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
	}*/

	public CoreTime getCoreTime() {
		return coreTime;
	}

	public void setCoreTime(CoreTime coreTime) {
		this.coreTime = coreTime;
	}

	public AttendanceTime(){
	}

	public LaborSystem getLaborSystem() {
		return laborSystem;
	}

	public void setLaborSystem(LaborSystem laborSystem) {
		this.laborSystem = laborSystem;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
