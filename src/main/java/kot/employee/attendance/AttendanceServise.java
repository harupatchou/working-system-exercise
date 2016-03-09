package main.java.kot.employee.attendance;

import main.java.kot.common.AttendanceTime;
import main.java.kot.common.WorkingDay;
import main.java.kot.dao.AttendanceTimeDao;
import main.java.kot.dao.WorkingDayDao;
import main.java.kot.logic.GetDataLogic;

public class AttendanceServise {

	//出勤情報の送信
	public static boolean insertWorkingDay(WorkingDay workingDay){
		return WorkingDayDao.insertWorkingDay(workingDay);
	}
	/*従業員種別IDから従業員種別情報を取得*/
	public static AttendanceTime getAttendanceTimeFromWorkingtypeId(Integer workingtypeId){
		return AttendanceTimeDao.getAttendanceTimeFromWrokingtypeId(workingtypeId);
	}
	/*従業員IDから従業員種別情報を取得*/
	public static AttendanceTime getAttendanceTimeFromEmployeeId(Integer employeeId){
		int workingtypeId = GetDataLogic.getWorkingtypeFromEmployeeId(employeeId).getId();
		return getAttendanceTimeFromWorkingtypeId(workingtypeId);
	}

}
