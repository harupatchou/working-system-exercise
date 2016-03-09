package main.java.kot.employee.attendance;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.common.Overtime;
import main.java.kot.common.WorkingAll;
import main.java.kot.common.WorkingDay;
import main.java.kot.logic.DateLogic;
import main.java.kot.logic.GenelalLogic;
import main.java.kot.logic.OvertimeLogic;

@WebServlet("/employee/Attendance")
public class AttendanceServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		WorkingDay workingDay = new WorkingDay();
		WorkingAll workingAll = new WorkingAll();

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		//TODO 決め打ち
		Integer employeeId = 1;

		String year =req.getParameter("year");
		String month =req.getParameter("month");
		String day =req.getParameter("day");

		List<String> attendDate = new ArrayList<String>();

		attendDate.add(year);
		attendDate.add(month);
		attendDate.add(day);

		String insertDate = GenelalLogic.joinString(attendDate, "-");

		//working_day Tableにinsert
		String startTime =req.getParameter("startTime");
		String endTime =req.getParameter("endTime");

		String[] breakStartTime = DateLogic.timeStr(req.getParameter("breakStartTime"));
		String[] breakEndTime = DateLogic.timeStr(req.getParameter("breakEndTime"));

		//一日の休憩時間算出
		String breakTime = DateLogic.getStringTime(breakStartTime, breakEndTime);

		try {
			workingDay.setDate(sdf.parse(insertDate));
			//TODO 決め打ち
			workingDay.setWeek(1);
			workingDay.setAttendanceTime(startTime);
			workingDay.setLeaveTime(endTime);
			workingDay.setBreakTime(breakTime);
			//TODO 決め打ち
			workingDay.setNapTime(breakTime);
			workingDay.setEmployeeId(employeeId);
			workingDay.setLegalFlag(1);
			// ここまで
		} catch (ParseException e) {
			e.printStackTrace();
		}

		AttendanceServise.insertWorkingDay(workingDay);

		//一日の残業時間算出
		Overtime overtime = OvertimeLogic.getOvertime(workingDay);

		//working_all Tableにinsert

		//00:00形式に変換
		String[] startTimeStr = DateLogic.timeStr(startTime);
		String[] endTimeStr = DateLogic.timeStr(endTime);

		//一日の総労働時間算出
		String attendDayAll = DateLogic.getStringTime(startTimeStr,endTimeStr);

		//一日の実労働時間算出
		String attendDay= DateLogic.getCalculateTime(attendDayAll,breakTime);

		//TODO 深夜計算 今は決め打ち
		String nightTime = "00:00";
		String nightOvertime = "00:00";

		//TODO 遅刻早退 今は決め打ち
		String lateTime = "00:00";

		//TODO 休日出勤 今は決め打ち
		String legalHolidayTime = "00:00";
		String statutoryHolidayTime = "00:00";

		try {
			workingAll.setDate(sdf.parse(insertDate));
			//決め打ち
			workingAll.setWeek(1);
			workingAll.setWorkingTimeAll(attendDay);
			workingAll.setLegalOvertimeAll(overtime.getLegalOvertime());
			workingAll.setSatutoryOverTimeAll(overtime.getStatutoryLeagalOvertime());
			workingAll.setNightTimeAll(nightTime);
			workingAll.setNightOvertimeAll(nightOvertime);
			workingAll.setLateTimeAll(lateTime);
			workingAll.setLegalHolidayTimeAll(legalHolidayTime);
			workingAll.setStatutoryHolidayTimeAll(statutoryHolidayTime);
			workingAll.setEmployeeId(employeeId);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		//残業にinsertするためにworkingDayTableのIDを取得
		WorkingDay insertDay =AttendanceServise.selectByDayAndEmployeeId(insertDate, employeeId);


		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
