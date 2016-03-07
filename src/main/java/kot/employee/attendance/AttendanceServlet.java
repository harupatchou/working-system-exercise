package main.java.kot.employee.attendance;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.jp.kot.admin.calculation.CalculationWorkingTimeService;
import main.java.jp.kot.admin.dao.EmployeeDao;

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

		String stringEmployeeId =req.getParameter("employeeId");
		Integer employeeId = Integer.parseInt(stringEmployeeId);
		String stringMonth = req.getParameter("month");
		Integer month = Integer.parseInt(stringMonth);

		req.setAttribute("workingTimeTotal", CalculationWorkingTimeService.workingTimeTotal(employeeId, month));
		req.setAttribute("employee", EmployeeDao.getEmployee(employeeId));
		req.setAttribute("workingtype", CalculationWorkingTimeService.getWorkingtypeFromEmployeeId(employeeId));

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
