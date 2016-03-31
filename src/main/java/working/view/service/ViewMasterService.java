package main.java.working.view.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.working.common.SelectDate;
import main.java.working.common.service.Service;
import main.java.working.dao.EmployeeDao;
import main.java.working.entity.Company;
import main.java.working.entity.Employee;
import main.java.working.logic.DataLogic;
import main.java.working.logic.DateLogic;

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
