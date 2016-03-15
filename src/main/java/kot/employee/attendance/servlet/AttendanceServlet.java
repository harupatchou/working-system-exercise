package main.java.kot.employee.attendance.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.InsertDay;
import main.java.kot.common.Schedule;
import main.java.kot.dao.WorkingDayDao;
import main.java.kot.employee.attendance.service.AttendanceServise;
import main.java.kot.employee.attendance.service.OvertimeService;
import main.java.kot.entity.Overtime;
import main.java.kot.entity.WorkingAll;
import main.java.kot.entity.WorkingDay;
import main.java.kot.entity.Workingtype;
import main.java.kot.logic.DataLogic;
import main.java.kot.logic.DateLogic;
import main.java.kot.logic.GeneralLogic;
import main.java.kot.logic.OvertimeLogic;
import main.java.kot.util.CalendarUtil;

@WebServlet("/employee/Attendance")
public class AttendanceServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");
		//セッション情報の取得
		HttpSession session=req.getSession();
		Integer userId = (Integer) session.getAttribute("loginId");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		WorkingDay workingDay = new WorkingDay();

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

		workingDay = WorkingDayDao.selectByDayAndEmployeeId(serverSideDate, userId);

		if(workingDay.getAttendanceTime()!=null){
			workingDay.setAttendanceTime(DateLogic.formatTime(workingDay.getAttendanceTime()));
			workingDay.setLeaveTime(DateLogic.formatTime(workingDay.getLeaveTime()));
		}

		req.setAttribute("selectDay", selectDay);
		req.setAttribute("workingDay", workingDay);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/daily/dailyAttendance.jsp");

		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");
		//セッション情報取得
		HttpSession session=req.getSession();
		Integer employeeId = (Integer) session.getAttribute("loginId");

		WorkingDay workingDay = new WorkingDay();
		WorkingAll workingAll = new WorkingAll();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		//TODO 法定休日決め打ちなので設定できるようにした方が？
		//TODO 現状画面側でユーザに00:00形式での入力必須だが、0:00形式でもokに変更すべき


		//insertする日を格納しておく
		InsertDay insertDay = new InsertDay();

		insertDay.setDay(Integer.parseInt(req.getParameter("day")));
		insertDay.setDayStr(req.getParameter("day"));
		insertDay.setMonth(Integer.parseInt(req.getParameter("month")));
		insertDay.setMonthStr(req.getParameter("month"));
		insertDay.setYear(Integer.parseInt(req.getParameter("year")));
		insertDay.setYearStr(req.getParameter("year"));

		//年月日から曜日の値取得
		Schedule weekInfo = CalendarUtil.getWeek(insertDay.getYear(),insertDay.getMonth(),insertDay.getDay());

		List<String> attendDate = new ArrayList<String>();

		//string型のinsertする形式にするために使用
		attendDate.add(insertDay.getYearStr());
		attendDate.add(insertDay.getMonthStr());
		attendDate.add(insertDay.getDayStr());

		String insertDate = GeneralLogic.joinString(attendDate, "-");

		//TODO 法定休日判別 現状は法定休日が土曜なためweekNumが1だが、設定で変更できるように変更すべき？
		if((weekInfo.getHolidayFlag() == 1) && (weekInfo.getWeekNum() == 1)){
			workingDay.setLegalFlag(1);
		}else{
			workingDay.setLegalFlag(0);
		}

		//working_day Tableにinsert
		String startTime =req.getParameter("startTime");
		String endTime =req.getParameter("endTime");

		//00:00形式を0:00形式に
		DateLogic.formatTime(startTime);
		DateLogic.formatTime(endTime);

		//working_day.insert用
		String breakStartTime =req.getParameter("breakStartTime");
		String breakEndTime =req.getParameter("breakEndTime");

		//計算用
		String[] tempBreakStartTime = DateLogic.timeStr(req.getParameter("breakStartTime"));
		String[] tempBreakEndTime2 = DateLogic.timeStr(req.getParameter("breakEndTime"));

		//一日の休憩時間算出
		String breakTime = DateLogic.getStringTime(tempBreakStartTime, tempBreakEndTime2);

		try {
			workingDay.setDate(sdf.parse(insertDate));
			workingDay.setWeek(weekInfo.getWeekNum());
			workingDay.setAttendanceTime(startTime);
			workingDay.setLeaveTime(endTime);
			workingDay.setBreakTimeStart(breakStartTime);
			workingDay.setBreakTimeEnd(breakEndTime);
			workingDay.setEmployeeId(employeeId);
			//TODO 決め打ち
			workingDay.setNapTime(breakTime);
			// ここまで
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//String alertMessage = UpperLimitTimeLogic.decisionLimitTime(workingDay);
		//req.setAttribute("alertMessage",alertMessage);

		AttendanceServise.insertWorkingDay(workingDay);

		//一日の残業時間算出
		Overtime overtime = new Overtime();
		Workingtype workingType = DataLogic.getWorkingtypeFromEmployeeId(employeeId);
		if(workingType.getId() == 1){
			overtime = OvertimeLogic.getOvertime(workingDay);
		}else if(workingType.getId() == 2){
			overtime = OvertimeLogic.getIrregularWorkingHourSystemOvertime(workingDay);
		// TODO フレックス用
		}else if(workingType.getId() == 3){
			/*ここに実装*/
		}

		//working_all Tableにinsert
		//00:00形式に変換
		String[] startTimeStr = DateLogic.timeStr(startTime);
		String[] endTimeStr = DateLogic.timeStr(endTime);

		//一日の総労働時間算出
		String attendDayAll = DateLogic.getStringTime(startTimeStr,endTimeStr);

		//一日の実労働時間算出
		String attendDay= DateLogic.getCalculateTime(attendDayAll,breakTime);

		//TODO 深夜 今は決め打ち
		String nightTime = "0:00";
		String nightOvertime = "0:00";

		//TODO 遅刻 今は決め打ち
		String lateTime = "0:00";

		//TODO 休日出勤
		//休日ならば
		if(weekInfo.getHolidayFlag()==1){
			//法定休日ならば
			if(workingDay.getLegalFlag()==1){
				workingAll.setDayStatus("法定");
			}else{
				//TODO 土曜出勤,祝日出勤は何として扱うか
				workingAll.setDayStatus("所定");
			}
		}else{
			workingAll.setDayStatus("通常");
		}

		try {
			workingAll.setDate(sdf.parse(insertDate));
			workingAll.setWeek(weekInfo.getWeekNum());
			//TODO 決め打ち
			workingAll.setWorkingTimeAll(attendDay);
			workingAll.setLegalOvertimeAll(overtime.getLegalOvertime());
			workingAll.setStatutoryOverTimeAll(overtime.getStatutoryOvertime());
			workingAll.setNightTimeAll(nightTime);
			workingAll.setNightOvertimeAll(nightOvertime);
			workingAll.setLateTimeAll(lateTime);
			workingAll.setEmployeeId(employeeId);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//残業にinsertするためにworkingDayTableのIDを取得
		WorkingDay insertDayInfo =AttendanceServise.selectByDayAndEmployeeId(insertDate, employeeId);
		//出勤を押した日のidを残業のdailyIdと紐付け
		overtime.setDailyId(insertDayInfo.getId());

		OvertimeService.insertOvertime(overtime);

		AttendanceServise.insertWorkingAll(workingAll);

		resp.sendRedirect("/kot/employee/MonthlyAttendance");

	}

}
