package main.java.kot.employee.attendance.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.AttendanceData;
import main.java.kot.common.InsertDay;
import main.java.kot.common.Schedule;
import main.java.kot.common.StrTime;
import main.java.kot.dao.AttendanceStatusDao;
import main.java.kot.dao.AttendanceTimeDao;
import main.java.kot.dao.WorkingAllDao;
import main.java.kot.dao.WorkingDayDao;
import main.java.kot.entity.AttendanceStatus;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.Employee;
import main.java.kot.entity.Overtime;
import main.java.kot.entity.WorkingAll;
import main.java.kot.entity.WorkingDay;
import main.java.kot.logic.AttendanceLogic;
import main.java.kot.logic.DataLogic;
import main.java.kot.util.CalendarUtil;


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

	//attendance_time内の情報取得
	public static AttendanceTime selectAttendTime(Employee employee ,Integer labor_system_id) {
		return AttendanceTimeDao.getAttendanceTimeFromLaborSystemId(employee,labor_system_id);
	}

	public static AttendanceData setAttendanceData(HttpServletRequest req,HttpServletResponse resp) {
		//TODO 法定休日決め打ちなので設定できるようにした方が？
		//TODO 現状画面側でユーザに00:00形式での入力必須だが、0:00形式でもokに変更すべき
		//TODO 欠勤、有給の扱い
		//TODO 退社時間超えたら早退

		//セッション情報取得
		HttpSession session=req.getSession();
		Employee employee =  (Employee) session.getAttribute("sesEmployee");

		AttendanceData attendanceData = new AttendanceData();

		attendanceData.setEmployee(employee);
		attendanceData.setWorkingtype(DataLogic.getWorkingtypeFromEmployeeId(employee.getEmployeeId()));

		//insertする日付を設定
		InsertDay tempDay = new InsertDay();
		tempDay = AttendanceLogic.setInsertDay(req,attendanceData);
		attendanceData.setInsertDay(tempDay);

		//週毎の情報を設定
		Schedule weekInfo = CalendarUtil.getWeek(attendanceData.getInsertDay().getYear(),attendanceData.getInsertDay().getMonth(),attendanceData.getInsertDay().getDay());
		attendanceData.setSchedule(weekInfo);

		//時間情報の設定
		StrTime tempTime = new StrTime();
		tempTime = AttendanceLogic.setStrTime(req,attendanceData);
		attendanceData.setStrTime(tempTime);

		return attendanceData;

	}

	public static void insertAll(HttpServletRequest req, AttendanceData attendanceData) {
		WorkingDay workingDay = new WorkingDay();
		WorkingAll workingAll = new WorkingAll();
		Overtime overtime = new Overtime();

		workingDay = AttendanceLogic.setWorkingDay(req,attendanceData);
		attendanceData.setWorkingDay(workingDay);
		//dayInsert
		insertWorkingDay(workingDay);

		workingAll = AttendanceLogic.setWorkingAll(attendanceData);
		overtime = AttendanceLogic.setOverTime(attendanceData);
		attendanceData.setWorkingAll(workingAll);
		attendanceData.setOvertime(overtime);

		//allInsert
		insertWorkingAll(workingAll);
		//overtimeInsert
		OvertimeService.insertOvertime(overtime);

	}
}
