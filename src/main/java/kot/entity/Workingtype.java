package main.java.kot.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 従業員種別情報
 * @author kuro
 **/
public class Workingtype {

	//ID
	private Integer id;
	//種別名
	private String workingName;
	//会社
	private Company company;
	//労働制
	private LaborSystem laborSystem;
	//勤怠時間
	private AttendanceTime attendanceTime;

	public Workingtype(){
	}

	public Workingtype(Integer id, String workingName, Integer company_id,Company company,LaborSystem laborSystem, AttendanceTime attendanceTime){
		this.id = id;
		this.workingName = workingName;
		this.company = company;
		this.laborSystem = laborSystem;
		this.attendanceTime = attendanceTime;
	}


	public void getWorkingTypeByResultSet(ResultSet rs){
		try {

			this.setId(rs.getInt("id"));
			this.setWorkingName(rs.getString("working_name"));

			Company tempCompany = new Company();
			tempCompany.setId(rs.getInt("company_id"));
			this.setCompany(tempCompany);

			LaborSystem tempLabor = new LaborSystem();
			tempLabor.setId(rs.getInt("labor_system_id"));
			tempLabor.setLaborSystemName(rs.getString("labor_system_name"));
			this.setLaborSystem(tempLabor);

			AttendanceTime tempAttend = new AttendanceTime();
			tempAttend.setStartTime(rs.getString("start_time"));
			tempAttend.setEndTime(rs.getString("end_time"));

			CoreTime tempCore = new CoreTime();
			tempCore.setCoreTimeStrat(rs.getString("core_time_start"));
			tempCore.setCoreTimeEnd(rs.getString("core_time_end"));
			tempAttend.setCoreTime(tempCore);
			this.setAttendanceTime(tempAttend);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWorkingName() {
		return workingName;
	}
	public void setWorkingName(String workingName) {
		this.workingName = workingName;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public LaborSystem getLaborSystem() {
		return laborSystem;
	}
	public void setLaborSystem(LaborSystem loborSystem) {
		this.laborSystem = loborSystem;
	}
	public AttendanceTime getAttendanceTime() {
		return attendanceTime;
	}
	public void setAttendanceTime(AttendanceTime attendanceTime) {
		this.attendanceTime = attendanceTime;
	}


}
