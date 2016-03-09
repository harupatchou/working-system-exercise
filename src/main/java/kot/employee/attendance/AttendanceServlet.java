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

import main.java.kot.common.WorkingDay;
import main.java.kot.logic.DateLogic;
import main.java.kot.logic.GenelalLogic;

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

		Calendar cal = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		//TODO 決め打ち
		Integer employeeId = 1;
		String year =req.getParameter("year");
		String month =req.getParameter("month");
		String day =req.getParameter("day");

		List<String> attendDate = new ArrayList<String>();

		attendDate.add(year);
		attendDate.add(month);
		attendDate.add(day);

		String insertDate = GenelalLogic.joinString(attendDate, "/");

		String startTime =req.getParameter("startTime");
		String endTime =req.getParameter("endTime");

		String[] startTimes = DateLogic.timeStr(req.getParameter("startTime"));
		String[] endTimes = DateLogic.timeStr(req.getParameter("endTime"));

		//一日の労働時間算出
		String attendDay = DateLogic.attend(startTimes, endTimes,0);

		String[] breakStartTime = DateLogic.timeStr(req.getParameter("breakStartTime"));
		String[] breakEndTime = DateLogic.timeStr(req.getParameter("breakEndTime"));

		//一日の休憩時間算出
		String breakTime = DateLogic.attend(breakStartTime, breakEndTime,1);


		//workingDayTableにinsert
		try {
			workingDay.setDate(sdf.parse(insertDate));
			//TODO 決め打ち
			workingDay.setWeek(1);
			workingDay.setAttendanceTime(startTime);
			workingDay.setLeaveTime(endTime);
			//TODO
			workingDay.setBreakTime(breakTime);
			workingDay.setNapTime(breakTime);
			workingDay.setEmployeeId(employeeId);
			workingDay.setLegalFlag(1);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		AttendanceServise.insertWorkingDay(workingDay);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
