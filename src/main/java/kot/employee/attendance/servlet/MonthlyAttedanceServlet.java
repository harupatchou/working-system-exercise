package main.java.kot.employee.attendance.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import main.java.kot.common.Schedule;
import main.java.kot.logic.DateLogic;
import main.java.kot.logic.GeneralLogic;

@WebServlet("/employee/MonthlyAttendance")
public class MonthlyAttedanceServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		HttpSession session=req.getSession();
		Integer userId = (Integer) session.getAttribute("loginId");
		String userName = (String) session.getAttribute("userName");

		req.setAttribute("userName", userName);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String strSelectMonth = req.getParameter("month");

		Calendar calendar = Calendar.getInstance();

		//calendarにsetするために当日のDate型の変数を用意
		Date today = new Date();
		calendar.setTime(today);

		int day_count = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		//selectを選択した場合と分岐
		if (strSelectMonth  != null){
			//選択した場合
			List<String> tempDate = new ArrayList<String>();

			//選択した年月と日付は1日を指定
			tempDate.add(req.getParameter("year"));
			tempDate.add(strSelectMonth);
			tempDate.add("1");

			String selectDate = GeneralLogic.joinString(tempDate, "/");

			try {
				//calendarにsetするために選択した月のDate型の変数を用意
				Date selectMonth = sdf.parse(selectDate);

				calendar.setTime(selectMonth);

				day_count = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}

		req.setAttribute("selectYear",calendar.get(Calendar.YEAR));
		req.setAttribute("selectMonth",calendar.get(Calendar.MONTH)+1);
		req.setAttribute("day_count", day_count);

		//画面表示用に3年前から2年後までの年を取得
		Integer tempYear = calendar.get(Calendar.YEAR)-3;

		List<Integer> yearList = new ArrayList<>();
		for(int i=0;i<6;i++){
			yearList.add(tempYear);
			tempYear += 1;
		}

		//画面表示用に月の情報を格納するList
		List<Schedule> scheduleList = DateLogic.getMonthlyDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, day_count,userId);

		req.setAttribute("scheduleList", scheduleList);
		req.setAttribute("yearList", yearList);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/monthly/monthlyAttendance.jsp");

		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
