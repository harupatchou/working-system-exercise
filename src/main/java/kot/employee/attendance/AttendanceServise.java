package main.java.kot.employee.attendance;

import main.java.kot.admin.dao.EmployeeDao;
import main.java.kot.common.Employee;

public class AttendanceServise {

	//出勤情報の送信
	public static Employee getEmployee(Integer employeeId){
		return EmployeeDao.getEmployee(employeeId);
	}

}
