package main.java.kot.logic;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.CalculationWorkingTimeTotal;
import main.java.kot.common.TempDate;
import main.java.kot.dao.CalculationWorkingTimeDao;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;


public class CalculationWorkingTimeLogic {

	//所属する従業員の月の総労働時間を取得
	private static List<CalculationWorkingTimeTotal> getCompanyCalculationWorkingTimeTotal(Company company,Integer year, Integer month){
		List<CalculationWorkingTimeTotal> calculationWorkingTimeTotalList = new ArrayList<>();
		List<Employee> employeeList = company.getEmployeeList();
		for(int i = 0; i < employeeList.size(); i++){
			CalculationWorkingTimeTotal total = CalculationWorkingTimeDao.workingTimeTotal(employeeList.get(i).getEmployeeId(), month, year);
			total.setEmployee(employeeList.get(i));
			total.setWorkingtype(DataLogic.getWorkingtypeFromEmployeeId(employeeList.get(i).getEmployeeId()));
			calculationWorkingTimeTotalList.add(total);
		}
		return calculationWorkingTimeTotalList;
	}


	/*CalculationWorkingTimeServletメイン処理委譲*/
	public static void CalculationworkingTime(HttpServletRequest req, HttpServletResponse resp){

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
		List<CalculationWorkingTimeTotal> CalculationWorkingTimeTotalList = getCompanyCalculationWorkingTimeTotal(company, year, month);

		req.setAttribute("workingTimeTotalList", CalculationWorkingTimeTotalList);
		req.setAttribute("date", date);
	}


}
