package main.java.kot.admin.setup.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.admin.setup.service.SetupService;
import main.java.kot.entity.Company;

@WebServlet("/master/CompanyEdit")
public class CompanyEditServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");
		//セッション情報取得
		HttpSession session=req.getSession();
		Company userCompany = (Company) session.getAttribute("sesCompany");

		req.setAttribute("userCompany", userCompany);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/setup/companyEdit.jsp");

		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");
		//セッション情報取得
		HttpSession session=req.getSession();
		Company userCompany = (Company) session.getAttribute("sesCompany");

		Company company = new Company();

		String stringCompanyId =req.getParameter("companyId");
		Integer companyId = Integer.parseInt(stringCompanyId);

		String companyName  =req.getParameter("companyName");

		company.setId(companyId);
		company.setCompanyName(companyName);

		SetupService.registCompany(company,userCompany);

		resp.sendRedirect("/kot/login");
	}

}
