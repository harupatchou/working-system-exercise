package main.java.working.admin.calculation.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.working.admin.calculation.logic.CalculationWorkingTimeLogic;
import main.java.working.common.CalculationWorkingTimeTotal;
import main.java.working.common.TempDate;
import main.java.working.common.service.Service;
import main.java.working.dao.EmployeeDao;
import main.java.working.entity.Company;
import main.java.working.entity.Employee;
import main.java.working.logic.DataLogic;

public class CalculationWorkingTimeService extends Service{

	@Override
	public void executeGet(HttpServletRequest req, HttpServletResponse resp) {

	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) {

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		Employee employee = DataLogic.getEmployee(loginId);
		Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompany().getId());

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

	}

}
