package main.java.kot.login.service;

import javax.servlet.http.HttpServlet;

import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Employee;

public class LoginServise extends HttpServlet{

	//employee_idからログインチェック
	public static Employee LoginCheckInfo(Integer employeeId){
		return EmployeeDao.LoginCheckInfo(employeeId);
	}

}