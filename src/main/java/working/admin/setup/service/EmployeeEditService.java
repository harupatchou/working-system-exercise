package main.java.working.admin.setup.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.working.admin.setup.logic.MasterSetupLogic;
import main.java.working.common.service.Service;
import main.java.working.dao.EmployeeDao;
import main.java.working.entity.Company;
import main.java.working.entity.Employee;
import main.java.working.entity.Workingtype;
import main.java.working.logic.DataLogic;

public class EmployeeEditService extends Service {

	@Override
	public void executeGet(HttpServletRequest req, HttpServletResponse resp) {

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		//従業員種別リスト
		Employee employee = DataLogic.getEmployee(loginId);
		Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompany().getId());
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

	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) {

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

		Workingtype workingtype = new Workingtype();
		workingtype.setId(workingtypeId);
		employee.setWorkingType(workingtype);
		employee.setPassword(password);
		employee.setCompany(sessEmployee.getCompany());

		//従業員登録
		MasterSetupLogic.registEmployee(employee);

	}

}
