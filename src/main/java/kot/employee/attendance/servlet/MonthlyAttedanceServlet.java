package main.java.kot.employee.attendance.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.common.Schedule;
import main.java.kot.logic.DateLogic;

@WebServlet("/employee/MonthlyAttendance")
public class MonthlyAttedanceServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		Calendar  calendar = Calendar.getInstance();
		//calendarにsetするために当日のDate型の変数を用意
		Date today = new Date();
		calendar.setTime(today);

		int day_count = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		req.setAttribute("selectYear",calendar.get(Calendar.YEAR));
		req.setAttribute("selectMonth", calendar.get(Calendar.MONTH)+1);
		req.setAttribute("day_count", day_count);

		//画面表示用に月の情報を格納するList
		List<Schedule> scheduleList = DateLogic.getMonthlyDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, day_count);

		req.setAttribute("scheduleList", scheduleList);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/monthly/monthlyAttendance.jsp");

		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
