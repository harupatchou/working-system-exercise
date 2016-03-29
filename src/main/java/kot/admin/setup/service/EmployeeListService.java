package main.java.kot.admin.setup.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.service.Service;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.logic.DataLogic;

public class EmployeeListService extends Service {

	@Override
	public void executeGet(HttpServletRequest req, HttpServletResponse resp) {

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		//従業員情報
		Employee employee = DataLogic.getEmployee(loginId);
		Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompany().getId());

		req.setAttribute("employeeList", company.getEmployeeList());

	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) {

	}

}
