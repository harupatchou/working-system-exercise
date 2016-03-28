package main.java.kot.employee.attendance.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MonthlyAttendanceService {

	/* MonthlyAttendanceServlet委譲処理 */
	public void monthlyAttendance(HttpServletRequest req, HttpServletResponse resp);

}
