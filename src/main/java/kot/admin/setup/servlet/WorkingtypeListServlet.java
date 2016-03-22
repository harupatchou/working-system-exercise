package main.java.kot.admin.setup.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.dao.EmployeeDao;
import main.java.kot.dao.WorkingtypeDao;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.entity.Workingtype;
import main.java.kot.logic.DataLogic;

@WebServlet("/master/WorkingtypeList")
public class WorkingtypeListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		//従業員種別情報
		Employee employee = DataLogic.getEmployee(loginId);
		Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompanyId());
		company = DataLogic.getWorkingTypeOfCompany(company);

		req.setAttribute("workingtypeList", company.getWorkingtypeList());

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/setup/working_type/workingTypeList.jsp");

		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		Workingtype workingtype = new Workingtype();

		String workingName =req.getParameter("workingName");

		String stringWorkingtypeId =req.getParameter("workingtypeId");
		Integer workingtypeId = Integer.parseInt(stringWorkingtypeId);

		String stringLaborSystemId =req.getParameter("laborSystemId");
		Integer laborSystemId = Integer.parseInt(stringLaborSystemId);
		/* TODO 決め打ち */
		Integer companyId = 1;


		workingtype.setId(workingtypeId);
		workingtype.setWorkingName(workingName);
		workingtype.setLaborSystemId(laborSystemId);
		workingtype.setCompanyId(companyId);

		WorkingtypeDao.registWorkingtype(workingtype);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
