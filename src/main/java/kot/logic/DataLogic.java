package main.java.kot.logic;

import main.java.kot.dao.AttendanceTimeDao;
import main.java.kot.dao.CompanyDao;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.dao.WorkingtypeDao;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.entity.Workingtype;

public class DataLogic {

	/*従業員IDから従業員情報を取得*/
	public static Employee getEmployee(Integer employeeId){
		return EmployeeDao.getEmployee(employeeId);
	}

	/*従業員IDから従業員種別情報を取得*/
	public static Workingtype getWorkingtypeFromEmployeeId(Integer employeeId){
		Integer workingtypeId = getEmployee(employeeId).getWorkingTypeId();
		return getWorkingtype(workingtypeId);
	}
	/*従業員IDから対応従業員種別の勤務時間を取得*/
	public static AttendanceTime getAttendanceTimeFromEmployeeId(Integer employeeId){
		int workingtypeId = getWorkingtypeFromEmployeeId(employeeId).getId();
		return getAttendanceTimeFromWorkingtypeId(workingtypeId);
	}
	/*会社IDから会社情報を取得*/
	public static Company getCompany(Integer companyId){
		return CompanyDao.getCompany(companyId);
	}

	/*従業員種別IDから従業員種別情報を取得*/
	public static Workingtype getWorkingtype(Integer workingtypeId){
		return WorkingtypeDao.getWorkingtype(workingtypeId);
	}

	/*従業員種別IDから従業員種別の勤務情報を取得*/
	public static AttendanceTime getAttendanceTimeFromWorkingtypeId(Integer workingtypeId){
		return AttendanceTimeDao.getAttendanceTimeFromWrokingtypeId(workingtypeId);
	}
}
