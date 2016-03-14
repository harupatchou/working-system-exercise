package main.java.kot.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.common.SelectDate;
import main.java.kot.dao.WorkingTimeDao;

@WebServlet("/master/Top")
public class ViewMasterSevlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		// TODO 検索IDの値はセッションから取る(現状決め打ち)
		SelectDate selectDate = WorkingTimeDao.getYearAndMonth(2);
		req.setAttribute("selectYear", selectDate.getYearList());
		req.setAttribute("selectMonth", selectDate.getMonthList());

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/index.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
