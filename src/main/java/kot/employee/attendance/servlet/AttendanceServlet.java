package main.java.kot.employee.attendance.servlet;

import java.io.IOException;

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

	/* Serviceの呼び出し */
	private static void serviceInvocation(HttpServletRequest req, HttpServletResponse resp, Integer reqParam){
		req.setAttribute("reqParam", reqParam);
		AttendanceServise attendanceService = new AttendanceServiceImpl();
		attendanceService.attendance(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//Serviceの呼び出し
		serviceInvocation(req, resp, 0);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/daily/dailyAttendance.jsp");

		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//Serviceの呼び出し
		serviceInvocation(req, resp, 1);

		resp.sendRedirect("/kot/employee/MonthlyAttendance");

	}
}
