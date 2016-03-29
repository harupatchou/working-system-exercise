package main.java.kot.view.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.common.service.ServiceConstant;
import main.java.kot.view.service.ViewMasterService;
import main.java.kot.view.serviceImpl.ViewMasterServiceImpl;

@WebServlet("/master/Top")
public class ViewMasterSevlet extends HttpServlet {

	/* Serviceの呼び出し */
	private static void serviceInvocation(HttpServletRequest req, HttpServletResponse resp, Integer reqParam){
		req.setAttribute("reqParam", reqParam);
		ViewMasterService viewMasterService = new ViewMasterServiceImpl();
		viewMasterService.viewMaster(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//Serviceの呼び出し
		serviceInvocation(req, resp, ServiceConstant.GET_REQUEST);

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
