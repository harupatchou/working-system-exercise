package main.java.kot.admin.setup;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.admin.dao.WorkingtypeDao;
import main.java.kot.common.Workingtype;

@WebServlet("/master/WorkingtypeEdit")
public class WorkingtypeEditServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/setup/workingtypeEdit.jsp");

		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		Workingtype workingtype = new Workingtype();

		String workingName =req.getParameter("workingName");

		String stringWorkingtypeId =req.getParameter("workingtypeId");
		Integer workingtypeId = Integer.parseInt(stringWorkingtypeId);

		String stringLaborSystemId =req.getParameter("laborSystemId");


		if(stringLaborSystemId != null){
			Integer laborSystemId = Integer.parseInt(stringLaborSystemId);
			workingtype.setLaborSystemId(laborSystemId);
		}else{
			/* TODO 決め打ち */
			workingtype.setLaborSystemId(1);
		}



		workingtype.setId(workingtypeId);
		workingtype.setWorkingName(workingName);

		WorkingtypeDao.registWorkingtype(workingtype);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
