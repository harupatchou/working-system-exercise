package main.java.working.entity;
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
	//コアタイム
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
