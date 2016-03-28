package main.java.kot.window.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AttendanceTimeService {

	/* AttendanceServlet委譲処理 */
	public void attendanceTime(HttpServletRequest req, HttpServletResponse resp);

}
