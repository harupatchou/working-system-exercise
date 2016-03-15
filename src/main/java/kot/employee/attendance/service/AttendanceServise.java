package main.java.kot.employee.attendance.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import main.java.kot.dao.AttendanceStatusDao;
import main.java.kot.dao.WorkingAllDao;
import main.java.kot.dao.WorkingDayDao;
import main.java.kot.entity.AttendanceStatus;
import main.java.kot.entity.WorkingAll;
import main.java.kot.entity.WorkingDay;


public class AttendanceServise {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	//出勤情報のinsert(day)
	public static boolean insertWorkingDay(WorkingDay workingDay){
		WorkingDay tempWorking = new WorkingDay();
		Date tempDate = workingDay.getDate();

		tempWorking = WorkingDayDao.selectByDayAndEmployeeId(sdf.format(tempDate), workingDay.getEmployeeId());

		if(tempWorking.getDate()==null){
			return WorkingDayDao.insertWorkingDay(workingDay);
		}else{
			return WorkingDayDao.updateWorkingDay(workingDay);
		}
	}

	//出勤情報のinsert(all)
	public static boolean insertWorkingAll(WorkingAll workingAll){
		WorkingAll tempWorking = new WorkingAll();
		Date tempDate = workingAll.getDate();

		tempWorking = WorkingAllDao.selectByDayAndEmployeeId(sdf.format(tempDate), workingAll.getEmployeeId());

		if(tempWorking.getDate()==null){
			return WorkingAllDao.insertWorkingAll(workingAll);
		}else{
			return WorkingAllDao.updateWorkingAll(workingAll);
		}
	}

	//working_day検索
	public static WorkingDay selectByDayAndEmployeeId(String selectDay,Integer employeeId){
		return WorkingDayDao.selectByDayAndEmployeeId(selectDay,employeeId);
	}

	//working_day検索(all)
	public static WorkingDay selectAllByEmployeeId(Integer year,Integer month,Integer day, Integer userId){
		return WorkingDayDao.selectAllByEmployeeId(year,month,day,userId);
	}

	//attendance_status内の情報取得
	public static List<AttendanceStatus> selectAttendStatusAll(){
		return AttendanceStatusDao.selectAttendStatusAll();
	}

}
