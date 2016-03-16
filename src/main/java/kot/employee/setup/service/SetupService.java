package main.java.kot.employee.setup.service;

import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Employee;

/**
 * セットアップ関連サービス
 *
 **/
public class SetupService {

	/*パスワード変更*/
	public static void changePassword(Employee employee){
		EmployeeDao.changePassword(employee);
	}

}
