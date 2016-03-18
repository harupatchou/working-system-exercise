package main.java.kot.login.service;

import javax.servlet.http.HttpServlet;

import main.java.kot.dao.CompanyDao;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;

public class LoginServise extends HttpServlet{

	//employee_idからログインチェック
	public static Employee LoginCheckInfo(Integer employeeId){
		return EmployeeDao.LoginCheckInfo(employeeId);
	}

	//employee_idからemployee情報取得
	public static Employee getSessionEmployee(Integer employeeId){
		return EmployeeDao.getEmployeeWithCompany(employeeId);
	}

	//company_idからcompany情報取得（紐付いている情報全て）
	public static Company getSessionCompany(Integer companyId) {
		return CompanyDao.getCompany(companyId);
	}

}
