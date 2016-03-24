package main.java.kot.view.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.view.service.ViewLoginService;
import main.java.kot.view.serviceImpl.ViewLoginServiceImpl;

@WebServlet("/login")
public class ViewLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//セッション破棄
		HttpSession session=req.getSession(true);
		session.invalidate();

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/login/login.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//処理を委譲したServiceの呼び出し
		req.setAttribute("reqParam", 1);
		ViewLoginService viewLoginService = new ViewLoginServiceImpl();
		viewLoginService.viewLogin(req, resp);

		resp.sendRedirect("/kot/login/check");
	}
}
