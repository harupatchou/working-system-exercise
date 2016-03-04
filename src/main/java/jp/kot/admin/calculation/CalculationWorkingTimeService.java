package main.java.jp.kot.admin.calculation;

import main.java.jp.kot.admin.dao.CompanyDao;
import main.java.jp.kot.admin.dao.EmployeeDao;
import main.java.jp.kot.admin.dao.WorkingTimeDao;
import main.java.jp.kot.admin.dao.WorkingtypeDao;
import main.java.jp.kot.common.Company;
import main.java.jp.kot.common.Employee;
import main.java.jp.kot.common.Workingtype;

public class CalculationWorkingTimeService {


	//従業員IDから従業員情報を取得
	public static Employee getEmployee(Integer employeeId){
		return EmployeeDao.getEmployee(employeeId);
	}

	//従業員IDから総労働時間を取得
	public static WorkingTimeTotal workingTimeTotal(Integer employeeId, Integer month){
		return WorkingTimeDao.workingTimeTotal(employeeId, month);
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
