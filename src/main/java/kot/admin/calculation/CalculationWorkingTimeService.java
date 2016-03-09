package main.java.kot.admin.calculation;

import main.java.kot.common.Company;
import main.java.kot.common.Employee;
import main.java.kot.common.Workingtype;
import main.java.kot.dao.CompanyDao;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.dao.WorkingTimeDao;
import main.java.kot.dao.WorkingtypeDao;

public class CalculationWorkingTimeService {


	//従業員IDから従業員情報を取得
	public static Employee getEmployee(Integer employeeId){
		return EmployeeDao.getEmployee(employeeId);
	}

	//従業員ID・年月から総労働時間を取得
	public static WorkingTimeTotal workingTimeTotal(Integer employeeId, Integer month,Integer year){
		return WorkingTimeDao.workingTimeTotal(employeeId, month,year);
	}

	//会社IDから会社情報を取得
	public static Company getCompany(Integer companyId){
		return CompanyDao.getCompany(companyId);
	}

	//従業員種別IDから従業員種別情報を取得
	public static Workingtype getWorkingtype(Integer workingtypeId){
		return WorkingtypeDao.getWorkingtype(workingtypeId);
	}

	//従業員IDから従業員種別情報を取得
	public static Workingtype getWorkingtypeFromEmployeeId(Integer employeeId){
		Integer workingtypeId = getEmployee(employeeId).getWorkingTypeId();
		return getWorkingtype(workingtypeId);
	}
}
