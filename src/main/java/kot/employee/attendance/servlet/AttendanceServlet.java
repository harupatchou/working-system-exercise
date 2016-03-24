package main.java.kot.employee.attendance.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.AttendanceData;
import main.java.kot.dao.WorkingDayDao;
import main.java.kot.employee.attendance.service.AttendanceServise;
import main.java.kot.entity.AttendanceStatus;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.Employee;
import main.java.kot.entity.WorkingDay;
import main.java.kot.logic.DateLogic;

@WebServlet("/employee/Attendance")
public class AttendanceServlet extends HttpServlet{

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");
		//セッション情報の取得
		HttpSession session=req.getSession();
		Employee employee = (Employee) session.getAttribute("sesEmployee");

		WorkingDay workingDay = new WorkingDay();

		List<AttendanceStatus> attendanceStatus = AttendanceServise.selectAttendStatusAll();

		String selectDay = "";

		String strDay = req.getParameter("day_num");

		if(strDay != null){
			selectDay = DateLogic.formatDate(strDay);
		}else{
			//サイドバーから遷移した場合は当日の編集
			Date today = new Date();
			selectDay = sdf.format(today);
		}

		//画面から送られてきた「/」区切りの日付を「-」区切りに変換
		String serverSideDate = selectDay.replace("/","-");

		workingDay = WorkingDayDao.selectByDayAndEmployeeId(serverSideDate, employee.getEmployeeId());

		if(workingDay.getAttendanceTime()!=null){
			workingDay.setAttendanceTime(DateLogic.formatTime(workingDay.getAttendanceTime()));
			workingDay.setLeaveTime(DateLogic.formatTime(workingDay.getLeaveTime()));
		}

		req.setAttribute("selectDay", selectDay);
		req.setAttribute("workingDay", workingDay);
		req.setAttribute("attendanceStatus", attendanceStatus);

		//従業員の出社時間と退社時間を算出
		AttendanceTime attendanceTime = AttendanceServise.selectAttendTime(employee,employee.getWorkingType().getLaborSystemId());

		req.setAttribute("attendanceTime", attendanceTime);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/daily/dailyAttendance.jsp");

		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//attendanceに必要なものを取得
		AttendanceData attendanceData = new AttendanceData();
		attendanceData = AttendanceServise.setAttendanceData(req, resp);

		//insert処理
		AttendanceServise.insertAll(req,attendanceData);

		resp.sendRedirect("/kot/employee/MonthlyAttendance");

	}
}
