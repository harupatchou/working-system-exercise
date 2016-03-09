package main.java.kot.employee.attendance.service;

import main.java.kot.dao.WorkingDayDao;
import main.java.kot.entity.WorkingDay;


public class AttendanceServise {

	//出勤情報の送信
	public static boolean insertWorkingDay(WorkingDay workingDay){
		return WorkingDayDao.insertWorkingDay(workingDay);
	}

	//working_day検索
	public static WorkingDay selectByDayAndEmployeeId(String selectDay,Integer employeeId){
		return WorkingDayDao.selectByDayAndEmployeeId(selectDay,employeeId);
	}

}
