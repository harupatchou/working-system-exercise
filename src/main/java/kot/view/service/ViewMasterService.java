package main.java.kot.view.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.SelectDate;
import main.java.kot.common.service.Service;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.logic.DataLogic;
import main.java.kot.logic.DateLogic;

public class ViewMasterService extends Service {

	@Override
	public void executeGet(HttpServletRequest req, HttpServletResponse resp) {

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		Employee employee = DataLogic.getEmployee(loginId);
		Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompany().getId());
		//会社IDから年月取得
		SelectDate selectDate = DateLogic.employeeDateSummary(company);
		req.setAttribute("selectYear", selectDate.getYearList());
		req.setAttribute("selectMonth", selectDate.getMonthList());

	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) {

	}

}
