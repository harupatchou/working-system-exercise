package main.java.working.login.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.working.common.service.ServiceConstant;
import main.java.working.login.service.LoginService;
import main.java.working.login.serviceImpl.LoginServiceImpl;

@WebServlet("/login/check")
public class LoginServlet extends HttpServlet{

	/* Serviceの呼び出し */
	private static String serviceInvocation(HttpServletRequest req, HttpServletResponse resp, Integer reqParam){
		req.setAttribute("reqParam", reqParam);
		LoginService loginService = new LoginServiceImpl();
		String url = loginService.Login(req, resp);
		return url;
	}

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

		//Serviceの呼び出し
		String url = serviceInvocation(req, resp, ServiceConstant.POST_REQUEST);

		resp.sendRedirect(url);
	}

}
