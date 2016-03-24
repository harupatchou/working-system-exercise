package main.java.kot.view.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.view.service.ViewEmployeeService;
import main.java.kot.view.serviceImpl.ViewEmployeeServiceImpl;

@WebServlet("/employee/Top")
public class ViewEmployeeSevlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//処理を委譲したServiceの呼び出し
		req.setAttribute("reqParam", 0);
		ViewEmployeeService viewEmployeeService = new ViewEmployeeServiceImpl();
		viewEmployeeService.viewEmployee(req, resp);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/top/Top.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
