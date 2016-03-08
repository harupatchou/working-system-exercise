package main.java.kot.admin.calculation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.dao.EmployeeDao;
import main.java.kot.logic.NonLegalWorkingTimeLogic;

@WebServlet("/master/Calculation")
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
		String stringYear = req.getParameter("year");
		Integer year = Integer.parseInt(stringYear);
		String stringMonth = req.getParameter("month");
		Integer month = Integer.parseInt(stringMonth);


		//ロジック実装確認用、後で消す
		NonLegalWorkingTimeLogic.getOvertime();

		req.setAttribute("workingTimeTotal", CalculationWorkingTimeService.workingTimeTotal(employeeId, month,year));
		req.setAttribute("employee", EmployeeDao.getEmployee(employeeId));
		req.setAttribute("workingtype", CalculationWorkingTimeService.getWorkingtypeFromEmployeeId(employeeId));

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
