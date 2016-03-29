package main.java.kot.admin.calculation.serviceImpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.admin.calculation.logic.CalculationWorkingTimeLogic;
import main.java.kot.admin.calculation.service.CalculationWorkingTimeService;
import main.java.kot.common.CalculationWorkingTimeTotal;
import main.java.kot.common.TempDate;
import main.java.kot.common.service.ServiceConstant;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.logic.DataLogic;

public class CalculationWorkingTimeServiceImpl implements CalculationWorkingTimeService{

	@Override
	public void CalculationWorkingTime(HttpServletRequest req, HttpServletResponse resp){

		Integer reqParam = (Integer)req.getAttribute("reqParam");

		/* Get*/
		if(reqParam == ServiceConstant.GET_REQUEST){

			/* doGet側処理、今後追加する際はここに書く*/

		/* Post */
		}else if (reqParam == ServiceConstant.POST_REQUEST){

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

}
