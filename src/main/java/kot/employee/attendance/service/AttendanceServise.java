package main.java.kot.employee.attendance.service;

import main.java.kot.common.WorkingAll;
import main.java.kot.dao.WorkingAllDao;
import main.java.kot.dao.WorkingDayDao;
import main.java.kot.entity.WorkingDay;


public class AttendanceServise {

	//出勤情報のinsert(day)
	public static boolean insertWorkingDay(WorkingDay workingDay){
		return WorkingDayDao.insertWorkingDay(workingDay);
	}

	//出勤情報のinsert(all)
	public static boolean insertWorkingAll(WorkingAll workingAll){
		return WorkingAllDao.insertWorkingAll(workingAll);
	}

	//working_day検索
	public static WorkingDay selectByDayAndEmployeeId(String selectDay,Integer employeeId){
		return WorkingDayDao.selectByDayAndEmployeeId(selectDay,employeeId);
	}

}
