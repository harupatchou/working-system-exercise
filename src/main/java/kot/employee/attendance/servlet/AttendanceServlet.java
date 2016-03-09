package main.java.kot.employee.attendance.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.entity.WorkingDay;
import main.java.kot.logic.DateLogic;
import main.java.kot.logic.GenelalLogic;

@WebServlet("/employee/Attendance")
public class AttendanceServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		WorkingDay workingDay = new WorkingDay();

		String stringEmployeeId =req.getParameter("employeeId");
		Integer employeeId = Integer.parseInt(stringEmployeeId);
		String year =req.getParameter("year");
		String month =req.getParameter("month");
		String day =req.getParameter("day");

		List<String> attendDate = new ArrayList<String>();

		attendDate.add(year);
		attendDate.add(month);
		attendDate.add(day);

		String insertDate = GenelalLogic.joinString(attendDate, "-");

		String[] startTime = DateLogic.timeStr(req.getParameter("startTime"));
		String[] endTime = DateLogic.timeStr(req.getParameter("endTime"));

		//一日の労働時間算出
		String attendDay = DateLogic.attend(startTime, endTime);

		String[] breakStartTime = DateLogic.timeStr(req.getParameter("breakStartTime"));
		String[] breakEndTime = DateLogic.timeStr(req.getParameter("breakEndTime"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");

		try {
			workingDay.setDate(sdf.parse(insertDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
