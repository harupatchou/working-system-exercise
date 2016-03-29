package main.java.kot.window.servlet;

import main.java.kot.window.service.AttendanceTimeService;
import main.java.kot.window.serviceimpl.AttendanceTimeServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/window/attendanceTime")
public class AttendanceTimeServlet extends HttpServlet {

	/* Serviceの呼び出し */
	//TODO 切り出した目的は？
	private static void serviceInvocation(HttpServletRequest req, HttpServletResponse resp, Integer reqParam){
		req.setAttribute("reqParam", reqParam); //FIXME なぜここでセットしてる？
		AttendanceTimeService attendanceTimeService = new AttendanceTimeServiceImpl(); //TODO ここでインスタンス化する意味は？
		attendanceTimeService.attendanceTime(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//Serviceの呼び出し
		serviceInvocation(req, resp, 0); //FIXME このゼロなにかな

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
