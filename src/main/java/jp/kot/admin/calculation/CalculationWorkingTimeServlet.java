package main.java.jp.kot.admin.calculation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.jp.kot.admin.dao.EmployeeDao;
import main.java.jp.kot.admin.logic.CalculationWorkingTimeLogic;

@WebServlet("/Calculation")
public class CalculationWorkingTimeServlet extends HttpServlet{

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

		req.setAttribute("workingTimeTotal", CalculationWorkingTimeLogic.workingTimeTotal(employeeId));
		req.setAttribute("employee", EmployeeDao.getEmployee(employeeId));

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
