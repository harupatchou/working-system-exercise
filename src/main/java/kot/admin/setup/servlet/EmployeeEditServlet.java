package main.java.kot.admin.setup.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Employee;

@WebServlet("/master/EmployeeEdit")
public class EmployeeEditServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/setup/employeeEdit.jsp");

		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		Employee employee = new Employee();

		String firstName =req.getParameter("firstName");
		String lastName =req.getParameter("lastName");
		String stringEmployeeId =req.getParameter("employeeId");
		Integer employeeId = Integer.parseInt(stringEmployeeId);
		String stringCompanyId =req.getParameter("companyId");

		if(stringCompanyId != null){
			Integer companyId = Integer.parseInt(stringCompanyId);
			employee.setCompanyId(companyId);
		}else{
			/* TODO 決め打ち */
			employee.setCompanyId(1);
		}

		String password = req.getParameter("password");

		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setEmployeeId(employeeId);
		employee.setPassword(password);

		EmployeeDao.registEmployee(employee);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/working/calculation.jsp");
		rd.forward(req, resp);
	}

}
