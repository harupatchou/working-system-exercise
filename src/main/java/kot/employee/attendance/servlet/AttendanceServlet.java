package main.java.kot.employee.attendance.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.employee.attendance.service.AttendanceServise;
import main.java.kot.employee.attendance.serviceImpl.AttendanceServiceImpl;

@WebServlet("/employee/Attendance")
public class AttendanceServlet extends HttpServlet{

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//処理を委譲したServiceの呼び出し
		req.setAttribute("reqParam", 0);
		AttendanceServise attendanceService = new AttendanceServiceImpl();
		attendanceService.attendance(req, resp);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/daily/dailyAttendance.jsp");

		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//処理を委譲したServiceの呼び出し
		req.setAttribute("reqParam", 1);
		AttendanceServise attendanceService = new AttendanceServiceImpl();
		attendanceService.attendance(req, resp);

		resp.sendRedirect("/kot/employee/MonthlyAttendance");

	}
}
