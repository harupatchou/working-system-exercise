package main.java.kot.window.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.admin.setup.service.SetupService;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.Company;


@WebServlet("/window/attendanceTime")
public class AttendanceTimeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//セッション情報取得
		HttpSession session=req.getSession();
		Company userCompany = (Company) session.getAttribute("sesCompany");

		//勤怠時間関連取得
		List<AttendanceTime> attendanceTimeList = SetupService.getAttendanceTime(userCompany.getId());

		String aa = req.getParameter("laborSystemId");

		Integer laborSystemId = Integer.parseInt(aa);

		req.setAttribute("attendanceTimeList", attendanceTimeList);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/window/attendanceTime.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//セッション情報取得
		HttpSession session=req.getSession();

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/setup/passwordEdit.jsp");
		rd.forward(req, resp);
	}

}
