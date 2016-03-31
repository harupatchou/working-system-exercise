package main.java.working.employee.attendance.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.working.common.AttendanceData;
import main.java.working.common.InsertDay;
import main.java.working.common.Schedule;
import main.java.working.common.StrTime;
import main.java.working.common.service.Service;
import main.java.working.dao.OvertimeDao;
import main.java.working.dao.WorkingAllDao;
import main.java.working.dao.WorkingDayDao;
import main.java.working.employee.attendance.logic.AttendanceLogic;
import main.java.working.entity.AttendanceStatus;
import main.java.working.entity.AttendanceTime;
import main.java.working.entity.Employee;
import main.java.working.entity.Overtime;
import main.java.working.entity.WorkingAll;
import main.java.working.entity.WorkingDay;
import main.java.working.logic.DataLogic;
import main.java.working.logic.DateLogic;
import main.java.working.util.CalendarUtil;

public class AttendanceService extends Service {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	//出勤情報のinsert(day)
	public static boolean insertWorkingDay(WorkingDay workingDay){
		WorkingDay tempWorking = new WorkingDay();
		Date tempDate = workingDay.getDate();

		tempWorking = WorkingDayDao.selectByDayAndEmployeeId(sdf.format(tempDate), workingDay.getEmployeeId());

		if(tempWorking.getId()==null){
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

	//残業時間の送信
	private static boolean insertOvertime(Overtime overtime){
		boolean overtimeJudge = OvertimeDao.getOvertimeFromDailyId(overtime);

		if(overtimeJudge){
			return OvertimeDao.updateOvertime(overtime);
		}else{
			return OvertimeDao.insertOvertime(overtime);
		}
	}

	private static AttendanceData setAttendanceData(HttpServletRequest req,HttpServletResponse resp) {
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

	private void insertAll(HttpServletRequest req, AttendanceData attendanceData) {

		WorkingDay workingDay = new WorkingDay();
		WorkingAll workingAll = new WorkingAll();

		workingDay = AttendanceLogic.setWorkingDay(req,attendanceData);
		attendanceData.setWorkingDay(workingDay);
		//dayInsert
		insertWorkingDay(workingDay);

		workingAll = AttendanceLogic.setWorkingAll(attendanceData);
		attendanceData.setWorkingAll(workingAll);

		//allInsert
		insertWorkingAll(workingAll);
		//overtimeInsert
		insertOvertime(attendanceData.getOvertime());

	}

	@Override
	public void executeGet(HttpServletRequest req, HttpServletResponse resp) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		//セッション情報の取得
		HttpSession session=req.getSession();
		Employee employee = (Employee) session.getAttribute("sesEmployee");

		WorkingDay workingDay = new WorkingDay();

		List<AttendanceStatus> attendanceStatus = AttendanceLogic.selectAttendStatusAll();

		String selectDay = "";

		String strDay = req.getParameter("day_num");

		if(strDay != null){
			selectDay = DateLogic.formatDate(strDay);
		}else{
			//サイドバーから遷移した場合は当日の編集
			Date today = new Date();
			selectDay = sdf.format(today);
		}

		//画面から送られてきた「/」区切りの日付を「-」区切りに変換
		String serverSideDate = selectDay.replace("/","-");

		workingDay = WorkingDayDao.selectByDayAndEmployeeId(serverSideDate, employee.getEmployeeId());

		if(workingDay.getAttendanceTime()!=null){
			workingDay.setAttendanceTime(DateLogic.formatTime(workingDay.getAttendanceTime()));
			workingDay.setLeaveTime(DateLogic.formatTime(workingDay.getLeaveTime()));
		}

		req.setAttribute("selectDay", selectDay);
		req.setAttribute("workingDay", workingDay);
		req.setAttribute("attendanceStatus", attendanceStatus);

		//従業員の出社時間と退社時間を算出
		AttendanceTime attendanceTime = AttendanceLogic.selectAttendTime(employee);

		req.setAttribute("attendanceTime", attendanceTime);

	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) {

		//attendanceに必要なものを取得
		AttendanceData attendanceData = new AttendanceData();
		attendanceData = setAttendanceData(req, resp);

		//insert処理
		insertAll(req,attendanceData);

	}

}
