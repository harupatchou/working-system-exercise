package main.java.kot.admin.calculation;

import main.java.kot.admin.dao.CompanyDao;
import main.java.kot.admin.dao.EmployeeDao;
import main.java.kot.admin.dao.WorkingTimeDao;
import main.java.kot.admin.dao.WorkingtypeDao;
import main.java.kot.common.Company;
import main.java.kot.common.Employee;
import main.java.kot.common.Workingtype;

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

	//労働種別IDから労働種別情報を取得
	public static Workingtype getWorkingtype(Integer workingtypeId){
		return WorkingtypeDao.getWorkingtype(workingtypeId);
	}

	//従業員IDから労働種別情報を取得
	public static Workingtype getWorkingtypeFromEmployeeId(Integer employeeId){
		Integer companyId = getEmployee(employeeId).getCompanyId();
		Integer workingtypeId = getCompany(companyId).getWorkingtypeId();
		return getWorkingtype(workingtypeId);
	}
}