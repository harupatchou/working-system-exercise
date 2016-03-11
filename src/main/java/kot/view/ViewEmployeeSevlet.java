package main.java.kot.view;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/employee/Top")
public class ViewEmployeeSevlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		Calendar  calendar = Calendar.getInstance();
		//calendarにsetするために当日のDate型の変数を用意
		Date today = new Date();
		calendar.setTime(today);

		int day_count = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

		req.setAttribute("selectYear",calendar.get(Calendar.YEAR));
		req.setAttribute("selectMonth", calendar.get(Calendar.MONTH)+1);
		req.setAttribute("day_count", day_count);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/monthly/index.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
