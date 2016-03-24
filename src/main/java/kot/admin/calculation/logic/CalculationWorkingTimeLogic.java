package main.java.kot.admin.calculation.logic;

import java.util.ArrayList;
import java.util.List;

import main.java.kot.common.CalculationWorkingTimeTotal;
import main.java.kot.dao.CalculationWorkingTimeDao;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.logic.DataLogic;

public class CalculationWorkingTimeLogic {

	//従業員ID・年月から総労働時間を取得
	public static CalculationWorkingTimeTotal workingTimeTotal(Integer employeeId, Integer month,Integer year){
		return CalculationWorkingTimeDao.workingTimeTotal(employeeId, month,year);
	}

	//所属する従業員の月の総労働時間を取得
	public static List<CalculationWorkingTimeTotal> getCompanyCalculationWorkingTimeTotal(Company company,Integer year, Integer month){
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

}
