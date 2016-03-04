package main.java.jp.kot.admin.setup;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.jp.kot.admin.dao.WorkingtypeDao;
import main.java.jp.kot.common.Workingtype;

@WebServlet("/WorkingtypeEdit")
public class WorkingtypeEditServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/setup/workingtypeEdit.jsp");

		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		Workingtype workingtype = new Workingtype();

		String workingName =req.getParameter("workingName");
		String workingTime =req.getParameter("workingTime");

		String stringWorkingtypeId =req.getParameter("workingtypeId");
		Integer workingtypeId = Integer.parseInt(stringWorkingtypeId);

		workingtype.setId(workingtypeId);
		workingtype.setWorkingName(workingName);
		workingtype.setWorkingTime(workingTime);

		WorkingtypeDao.registWorkingtype(workingtype);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
