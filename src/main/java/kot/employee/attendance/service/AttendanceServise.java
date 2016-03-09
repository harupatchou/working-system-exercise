package main.java.kot.employee.attendance.service;

import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Employee;

public class AttendanceServise {

	//出勤情報の送信
	public static Employee getEmployee(Integer employeeId){
		return EmployeeDao.getEmployee(employeeId);
	}

}
