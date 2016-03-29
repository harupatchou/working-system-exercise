package main.java.kot.view.serviceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.LimitWorkingTime;
import main.java.kot.common.service.ServiceConstant;
import main.java.kot.entity.Employee;
import main.java.kot.logic.DataLogic;
import main.java.kot.logic.OvertimeLogic;
import main.java.kot.view.service.ViewEmployeeService;

public class ViewEmployeeServiceImpl implements ViewEmployeeService{

	@Override
	public void viewEmployee(HttpServletRequest req, HttpServletResponse resp){

		Integer reqParam = (Integer)req.getAttribute("reqParam");

		/* Get*/
		if(reqParam == ServiceConstant.GET_REQUEST){

			//セッション情報取得
			HttpSession session=req.getSession();
			int loginId = (Integer) session.getAttribute("loginId");

			//月次労働時間情報
			Employee employee = DataLogic.getEmployee(loginId);
			LimitWorkingTime limitWorkingtime = OvertimeLogic.getPossibleOvertime(employee);

			req.setAttribute("limitWorkingtime", limitWorkingtime);

		/* Post */
		}else if(reqParam == ServiceConstant.POST_REQUEST){

			/* doPost側処理、今後追加する際はここに書く*/

		}
	}
}
