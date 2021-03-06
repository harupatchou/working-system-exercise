package main.java.working.logic;

import java.util.List;

import main.java.working.dao.AttendanceTimeDao;
import main.java.working.dao.CompanyDao;
import main.java.working.dao.EmployeeDao;
import main.java.working.dao.WorkingtypeDao;
import main.java.working.entity.AttendanceTime;
import main.java.working.entity.Company;
import main.java.working.entity.Employee;
import main.java.working.entity.Workingtype;

public class DataLogic {

	/*従業員IDから従業員情報を取得*/
	public static Employee getEmployee(Integer employeeId){
		return EmployeeDao.getEmployee(employeeId);
	}

	/*従業員IDから従業員種別情報を取得*/
	public static Workingtype getWorkingtypeFromEmployeeId(Integer employeeId){
		Integer workingtypeId = getEmployee(employeeId).getWorkingType().getId();
		return getWorkingtype(workingtypeId);
	}
	/*従業員IDから対応従業員種別の勤務時間を取得*/
	public static AttendanceTime getAttendanceTimeFromEmployeeId(Integer employeeId){
		Employee employee = getEmployee(employeeId);
		return getAttendanceTimeFromLaborSystemId(employee);
	}
	/*会社IDから会社情報を取得*/
	public static Company getCompany(Integer companyId){
		return CompanyDao.getCompany(companyId);
	}
	/*会社の従業員種別を取得*/
	public static Company getWorkingTypeOfCompany(Company company){
		List<Workingtype> workingtypeList = WorkingtypeDao.getWorkingtypeFromCompanyId(company);
		company.setWorkingtypeList(workingtypeList);
		return company;
	}

	/*従業員種別IDから従業員種別情報を取得*/
	public static Workingtype getWorkingtype(Integer workingtypeId){
		return WorkingtypeDao.getWorkingtype(workingtypeId);
	}

	/*従業員種別IDから従業員種別の勤務情報を取得*/
	public static AttendanceTime getAttendanceTimeFromLaborSystemId(Employee employee){
		return AttendanceTimeDao.getAttendanceTimeFromLaborSystemId(employee);
	}
}
