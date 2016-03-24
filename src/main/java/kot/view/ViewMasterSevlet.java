package main.java.kot.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.SelectDate;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.logic.DataLogic;
import main.java.kot.logic.DateLogic;

@WebServlet("/master/Top")
public class ViewMasterSevlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		Employee employee = DataLogic.getEmployee(loginId);
		Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompany().getId());
		//会社IDから年月取得
		SelectDate selectDate = DateLogic.employeeDateSummary(company);
		req.setAttribute("selectYear", selectDate.getYearList());
		req.setAttribute("selectMonth", selectDate.getMonthList());

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/index.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
