package main.java.kot.admin.setup.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.common.service.ServiceConstant;
import main.java.kot.common.service.ServiceInvocation;

@WebServlet("/master/WorkingtypeList")
public class WorkingtypeListServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//Serviceの呼び出し
		ServiceInvocation.serviceInvocation(req, resp, ServiceConstant.GET_REQUEST, ServiceConstant.WORKING_TYPE);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/setup/working_type/workingTypeList.jsp");
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//Serviceの呼び出し
		ServiceInvocation.serviceInvocation(req, resp, ServiceConstant.POST_REQUEST, ServiceConstant.WORKING_TYPE);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
