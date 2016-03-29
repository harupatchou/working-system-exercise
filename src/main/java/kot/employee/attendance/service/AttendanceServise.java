package main.java.kot.employee.attendance.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AttendanceServise {

	/* AttendanceServlet委譲処理 */
	public void attendance(HttpServletRequest req,HttpServletResponse resp);

}
