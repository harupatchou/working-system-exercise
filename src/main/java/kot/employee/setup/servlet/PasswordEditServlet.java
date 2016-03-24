package main.java.kot.employee.setup.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.employee.setup.service.EmployeeSetupService;
import main.java.kot.employee.setup.serviceImpl.EmployeeSetupServiceImpl;
@WebServlet("/employee/PasswordEdit")
public class PasswordEditServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/setup/passwordEdit.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//処理を委譲したServiceの呼び出し
		req.setAttribute("reqParam", 1);
		EmployeeSetupService setupService = new EmployeeSetupServiceImpl();
		setupService.passwordEdit(req, resp);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/setup/passwordEdit.jsp");
		rd.forward(req, resp);
	}

}
