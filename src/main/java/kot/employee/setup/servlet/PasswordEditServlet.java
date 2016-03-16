package main.java.kot.employee.setup.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.employee.setup.service.SetupService;
import main.java.kot.entity.Employee;
import main.java.kot.logic.DataLogic;
@WebServlet("/employee/PasswordEdit")
public class PasswordEditServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/setup/passwordEdit.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		String password = req.getParameter("password");
		String newPassWord = req.getParameter("new_password");
		String confirmNewPassword = req.getParameter("confirm_new_password");

		Employee employee = DataLogic.getEmployee(loginId);
		employee.setNewPassword(newPassWord);
		//パスワードチェック
		String message = "";
		if(password.equals(employee.getPassword()) && newPassWord.equals(confirmNewPassword)){
			SetupService.changePassword(employee);
			message = "パスワード変更が完了しました";
		}else{

			if(!password.equals(employee.getPassword())){
				message = "現在のパスワードが間違っています";
			}else if(!newPassWord.equals(confirmNewPassword)){
				message = "新規パスワードと確認パスワードが一致しません";
			}
		}
		req.setAttribute("message", message);

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/employee/setup/passwordEdit.jsp");
		rd.forward(req, resp);
	}

}
