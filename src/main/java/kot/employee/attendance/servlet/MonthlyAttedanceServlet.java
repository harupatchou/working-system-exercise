package main.java.kot.employee.attendance.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.employee.attendance.service.MonthlyAttendanceService;
import main.java.kot.employee.attendance.serviceImpl.MonthlyAttendanceServiceImpl;

@WebServlet("/employee/MonthlyAttendance")
public class MonthlyAttedanceServlet extends HttpServlet{

	/* Serviceの呼び出し */
	private static void serviceInvocation(HttpServletRequest req, HttpServletResponse resp, Integer reqParam){
		req.setAttribute("reqParam", reqParam);
		MonthlyAttendanceService monthlyAttendanceService = new MonthlyAttendanceServiceImpl();
		monthlyAttendanceService.monthlyAttendance(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//serviceの呼び出し
		serviceInvocation(req, resp, 0);

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
