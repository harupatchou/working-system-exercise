package main.java.kot.employee.attendance;

import main.java.kot.common.WorkingDay;
import main.java.kot.dao.WorkingDayDao;

public class AttendanceServise {

	//出勤情報の送信
	public static boolean insertWorkingDay(WorkingDay workingDay){
		return WorkingDayDao.insertWorkingDay(workingDay);
	}

}
