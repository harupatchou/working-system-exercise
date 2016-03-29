package main.java.kot.view.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.LimitWorkingTime;
import main.java.kot.common.service.Service;
import main.java.kot.entity.Employee;
import main.java.kot.logic.DataLogic;
import main.java.kot.logic.OvertimeLogic;

public class ViewEmployeeService extends Service {

	@Override
	public void executeGet(HttpServletRequest req, HttpServletResponse resp) {

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		//月次労働時間情報
		Employee employee = DataLogic.getEmployee(loginId);
		LimitWorkingTime limitWorkingtime = OvertimeLogic.getPossibleOvertime(employee);

		req.setAttribute("limitWorkingtime", limitWorkingtime);

	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) {

	}

}
