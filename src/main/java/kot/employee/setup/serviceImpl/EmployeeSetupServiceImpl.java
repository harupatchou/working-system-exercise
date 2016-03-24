package main.java.kot.employee.setup.serviceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.dao.EmployeeDao;
import main.java.kot.employee.setup.service.EmployeeSetupService;
import main.java.kot.entity.Employee;
import main.java.kot.logic.DataLogic;

public class EmployeeSetupServiceImpl implements EmployeeSetupService{

	@Override
	public void passwordEdit(HttpServletRequest req, HttpServletResponse resp){

		Integer reqParam = (Integer)req.getAttribute("reqParam");

		/* Get*/
		if(reqParam == 0){

			/* doGet側処理、今後追加する際はここに書く*/

		/* Post */
		}else{

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
}
