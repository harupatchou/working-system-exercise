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

import main.java.kot.admin.setup.service.SetupService;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.logic.DataLogic;

@WebServlet("/master/EmployeeEdit")
public class EmployeeEditServlet extends HttpServlet{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		//従業員種別リスト
		Employee employee = DataLogic.getEmployee(loginId);
		Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompanyId());
		company = DataLogic.getWorkingTypeOfCompany(company);

		req.setAttribute("workingtypeList", company.getWorkingtypeList());

		//従業員情報
		Employee tempEmployee = new Employee();

		String strEmployeeId =req.getParameter("employeeId");
		if(strEmployeeId != null){
			Integer employeeId = Integer.parseInt(strEmployeeId);
			tempEmployee = DataLogic.getEmployee(employeeId);
		}

		req.setAttribute("employee", tempEmployee);


		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/setup/employee/employeeEdit.jsp");

		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		//従業員種別リスト
		Employee sessEmployee = DataLogic.getEmployee(loginId);

		Employee employee = new Employee();

		String firstName =req.getParameter("firstName");
		String lastName =req.getParameter("lastName");
		String strEmployeeId = req.getParameter("employeeId");
		Integer employeeId = Integer.parseInt(strEmployeeId);
		String strWorkingtypeId = req.getParameter("workingtypeId");
		Integer workingtypeId = Integer.parseInt(strWorkingtypeId);
		String password = req.getParameter("password");

		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setEmployeeId(employeeId);
		employee.setWorkingTypeId(workingtypeId);
		employee.setPassword(password);
		employee.setCompanyId(sessEmployee.getCompanyId());

		//従業員登録
		SetupService.registEmployee(employee);


		resp.sendRedirect("/kot/master/EmployeeList");
	}
}
