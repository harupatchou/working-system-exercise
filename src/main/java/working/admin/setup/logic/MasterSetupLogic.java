package main.java.working.admin.setup.logic;

import java.util.List;

import main.java.working.dao.AttendanceTimeDao;
import main.java.working.dao.CompanyDao;
import main.java.working.dao.EmployeeDao;
import main.java.working.dao.WorkingTimeDao;
import main.java.working.entity.AttendanceTime;
import main.java.working.entity.Company;
import main.java.working.entity.Employee;
import main.java.working.entity.WorkingTime;

public class MasterSetupLogic {

	//company情報の登録
	public static void registCompany(Company company,Company userCompany) {
		CompanyDao.editCompany(company,userCompany);
	}

	//従業員情報のinsert・update
	public static void registEmployee(Employee employee){
		Employee checkEmployee = EmployeeDao.getEmployee(employee.getEmployeeId());
		if(checkEmployee.getEmployeeId() == null){
			EmployeeDao.registEmployee(employee);
		}else{
			EmployeeDao.updateEmployee(employee);
		}
	}

	//company_idからcompany情報取得
	public static Company getCompany(Integer companyId) {
		return CompanyDao.getCompany(companyId);
	}

	//company_idからattendance_time情報取得
	public static List<AttendanceTime> getAttendanceTime(Integer id) {
		return AttendanceTimeDao.getAttendanceTime(id);
	}

	//working_type_idからworking_time情報取得
	public static WorkingTime getWorkingTime(Integer id) {
		return WorkingTimeDao.getWorkingTime(id);
	}

	//attendance_timeにinsert
	public static void registAttendTime(AttendanceTime attendanceTime) {
		AttendanceTimeDao.editAttendanceTime(attendanceTime);
	}

	//working_timeにinsert
	public static void registWorkingTime(WorkingTime workingTime) {
		WorkingTimeDao.editWorkingTime(workingTime);
	}


}
