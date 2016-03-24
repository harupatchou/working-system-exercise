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

import main.java.kot.admin.setup.logic.SetupLogic;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.Company;
import main.java.kot.entity.WorkingTime;


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
		List<AttendanceTime> attendanceTimeList = SetupLogic.getAttendanceTime(userCompany.getId());

		Integer laborSystemId = Integer.parseInt(req.getParameter("laborSystemId"));

		//通常の場合、出勤時間と退勤時間を編集する画面
		if(laborSystemId == 1 || laborSystemId == 2){
			String startTime = attendanceTimeList.get(laborSystemId-1).getStartTime();
			String endTime = attendanceTimeList.get(laborSystemId-1).getEndTime();

			req.setAttribute("startTime", startTime);
			req.setAttribute("endTime", endTime);
		}

		req.setAttribute("attendanceTimeList", attendanceTimeList);

		//workingTimeを取得
		WorkingTime workingTime = SetupLogic.getWorkingTime(laborSystemId);

		req.setAttribute("workingTime", workingTime);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/window/attendanceTime.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/setup/passwordEdit.jsp");
		rd.forward(req, resp);
	}

}
