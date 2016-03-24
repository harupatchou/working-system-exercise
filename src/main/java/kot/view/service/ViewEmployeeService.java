package main.java.kot.view.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewEmployeeService {

	/* ViewEmployeeServlet委譲処理*/
	public void viewEmployee(HttpServletRequest req, HttpServletResponse resp);

}
