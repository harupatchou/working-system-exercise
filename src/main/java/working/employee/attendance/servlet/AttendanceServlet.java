package main.java.working.employee.attendance.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.working.employee.attendance.service.AttendanceService;

@WebServlet("/employee/Attendance")
public class AttendanceServlet extends HttpServlet{

	private static AttendanceService attendanceService = new AttendanceService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//Serviceの呼び出し
		attendanceService.executeGet(req, resp);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/daily/dailyAttendance.jsp");

		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//Serviceの呼び出し
		attendanceService.executePost(req, resp);

		resp.sendRedirect("/working/employee/MonthlyAttendance");

	}
}
