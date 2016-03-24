package main.java.kot.admin.calculation.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.admin.calculation.logic.CalculationWorkingTimeLogic;
import main.java.kot.common.CalculationWorkingTimeTotal;
import main.java.kot.common.TempDate;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.logic.DataLogic;

public class CalculationWorkingTimeServiceImpl implements CalculationWorkingTimeService{

	@Override
	public void CalculationWorkingTime(HttpServletRequest req, HttpServletResponse resp){

		Integer reqParam = (Integer)req.getAttribute("reqParam");

		/* Get*/
		if(reqParam == 0){

			/*doGet側処理、ここに書く*/

		/* Post */
		}else{

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

		}


	}

}