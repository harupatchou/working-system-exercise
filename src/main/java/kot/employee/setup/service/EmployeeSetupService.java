package main.java.kot.employee.setup.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 従業員セットアップ関連サービス
 **/
public interface EmployeeSetupService {

	/*PasswordEditServlet委譲処理*/
	public void passwordEdit(HttpServletRequest req, HttpServletResponse resp);

}
