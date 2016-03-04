package main.java.jp.kot.admin.setup;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.jp.kot.admin.dao.CompanyDao;
import main.java.jp.kot.common.Company;

@WebServlet("/CompanyEdit")
public class CompanyEditServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/setup/companyEdit.jsp");

		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		Company company = new Company();

		String stringCompanyId =req.getParameter("companyId");
		Integer companyId = Integer.parseInt(stringCompanyId);

		String companyName  =req.getParameter("companyName");

		String stringWorkingtypeId =req.getParameter("workingtypeId");

		if(stringWorkingtypeId != null){
			Integer workingtypeId = Integer.parseInt(stringWorkingtypeId);
			company.setWorkingtypeId(workingtypeId);
		}else{
			/* TODO 決め打ち */
			company.setWorkingtypeId(1);
		}

		company.setId(companyId);
		company.setCompanyName(companyName);

		CompanyDao.registCompany(company);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
