package main.java.working.admin.setup.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.working.admin.setup.service.CompanyEditService;

@WebServlet("/master/CompanyEdit")
public class CompanyEditServlet extends HttpServlet{

	private static CompanyEditService companyEditService = new CompanyEditService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//Serviceの呼び出し
		companyEditService.executeGet(req, resp);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/setup/companyEdit.jsp");

		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//Serviceの呼び出し
		companyEditService.executePost(req, resp);

		resp.sendRedirect("/working/login");
	}

}
