package main.java.kot.employee.setup.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.service.Service;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Employee;
import main.java.kot.logic.DataLogic;

public class PasswordEditService extends Service {

	@Override
	public void executeGet(HttpServletRequest req, HttpServletResponse resp) {

	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) {

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		String password = req.getParameter("password");
		String newPassWord = req.getParameter("new_password");

		Employee employee = DataLogic.getEmployee(loginId);
		//パスワードチェック
		String message = "";
		if(password.equals(employee.getPassword())){
			employee.setPassword(newPassWord);
			EmployeeDao.changePassword(employee);
			message = "パスワード変更が完了しました";
		}else{

			if(!password.equals(employee.getPassword())){
				message = "現在のパスワードが間違っています";
			}
		}
		req.setAttribute("message", message);

	}

}
