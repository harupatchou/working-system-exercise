package main.java.kot.login.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.login.service.LoginServise;
import main.java.kot.login.session.LoginSession;

@WebServlet("/login/check")
public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		HttpSession session=req.getSession(false);

		LoginSession sess = new LoginSession();

		sess.setLogin_id((Integer) session.getAttribute("loginId"));
		sess.setPassword((String) session.getAttribute("password"));

		Employee checkInf = LoginServise.LoginCheckInfo((Integer) session.getAttribute("loginId"));

		if(checkInf.getEmployeeId() == null){
			//入力したidが存在しなければ
			resp.sendRedirect("/kot/login");
		}else{
			if(!checkInf.getPassword().equals(sess.getPassword())) {
				//passが一致していなければ
				resp.sendRedirect("/kot/login");
			}else{
				//一致していれば
				String userName = LoginServise.getSessionEmployee(sess.getLogin_id()).getFirstName();

				//従業員情報をセッションに格納
				Employee sesEmployee = LoginServise.getSessionEmployee(sess.getLogin_id());

				//会社情報をセッションに格納
				Company sesCompany = LoginServise.getSessionCompany(sesEmployee.getCompanyId());

				session.setAttribute("userName", userName);
				session.setAttribute("sesEmployee",sesEmployee);
				session.setAttribute("sesCompany",sesCompany);

				if(checkInf.getCompany().getMasterId() == sess.getLogin_id()){
					//employeeIdが紐付いたCompanyのmasterIDと一致していればmaster画面へ
					//一致していなければemployee画面へ
					resp.sendRedirect("/kot/master/Top");
				}else{
					resp.sendRedirect("/kot/employee/Top");
				}
			}
		}
	}

}
