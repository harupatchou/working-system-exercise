package main.java.kot.admin.setup.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * master画面setup関連サービス
 * @author ueno
 **/
public interface MasterSetupService {

	/*EmployeeListServlet委譲処理*/
	public void employeeList(HttpServletRequest req, HttpServletResponse resp);

	/*EmployeeEditServlet委譲処理*/
	public void employeeEdit(HttpServletRequest req, HttpServletResponse resp);

	/*WorkingtypeListServlet委譲処理*/
	public void workingtypeList(HttpServletRequest req, HttpServletResponse resp);

	/*CompanyEditServlet委譲処理*/
	public void companyEdit(HttpServletRequest req, HttpServletResponse resp);

}
