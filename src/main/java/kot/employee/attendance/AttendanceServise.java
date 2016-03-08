package main.java.kot.employee.attendance;

import main.java.kot.common.Employee;
import main.java.kot.dao.EmployeeDao;

public class AttendanceServise {

	//出勤情報の送信
	public static Employee getEmployee(Integer employeeId){
		return EmployeeDao.getEmployee(employeeId);
	}

}
