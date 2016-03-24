package main.java.kot.admin.calculation.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CalculationWorkingTimeService {

	/*CalculationWorkingTimeServlet委譲処理*/
	public void CalculationWorkingTime(HttpServletRequest req, HttpServletResponse resp);
}
