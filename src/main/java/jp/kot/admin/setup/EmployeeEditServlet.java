package main.java.jp.kot.admin.setup;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EmployeeEdit")
public class EmployeeEditServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/setup/employeeEdit.jsp");

		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		String firstName =req.getParameter("firstName");
		String lastName =req.getParameter("lastName");
		String stringEmployeeId =req.getParameter("employeeId");
		Integer employeeId = Integer.parseInt(stringEmployeeId);
		String stringCompanyId =req.getParameter("companyId");
		Integer companyId = Integer.parseInt(stringCompanyId);
		/** 現状workingtypeがないためコメントアウト */
		/*String stringWorkingtypeId =req.getParameter("workingtypeId");
		Integer workingtypeId = Integer.parseInt(stringWorkingtypeId);*/
		String password = req.getParameter("password");


		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
