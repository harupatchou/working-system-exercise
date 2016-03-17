package main.java.kot.admin.calculation.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.CalculationWorkingTimeTotal;
import main.java.kot.common.TempDate;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.logic.CalculationWorkingTimeLogic;
import main.java.kot.logic.DataLogic;

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

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		Employee employee = DataLogic.getEmployee(loginId);
		Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompanyId());

		String stringYear = req.getParameter("year");
		Integer year = Integer.parseInt(stringYear);
		String stringMonth = req.getParameter("month");
		Integer month = Integer.parseInt(stringMonth);

		TempDate date = new TempDate();
		date.setYear(year);
		date.setMonth(month);
		//企業に所属する各従業員の対象月の労働時間
		List<CalculationWorkingTimeTotal> CalculationWorkingTimeTotalList = CalculationWorkingTimeLogic.getCompanyCalculationWorkingTimeTotal(company, year, month);

		req.setAttribute("workingTimeTotalList", CalculationWorkingTimeTotalList);
		req.setAttribute("date", date);

		/*CalculationWorkingTimeTotal workingTimeTotal = CalculationWorkingTimeService.workingTimeTotal(employeeId, month,year);
		Employee employee = EmployeeDao.getEmployee(employeeId);
		Workingtype workingtype = DataLogic.getWorkingtypeFromEmployeeId(employeeId);

		req.setAttribute("workingTimeTotal", workingTimeTotal);
		req.setAttribute("employee", employee);
		req.setAttribute("workingtype", workingtype);*/

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
