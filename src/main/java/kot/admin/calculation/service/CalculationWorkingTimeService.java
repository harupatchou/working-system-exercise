package main.java.kot.admin.calculation.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.common.CalculationWorkingTimeTotal;
import main.java.kot.dao.CalculationWorkingTimeDao;
import main.java.kot.logic.CalculationWorkingTimeLogic;

public class CalculationWorkingTimeService {

	//従業員ID・年月から総労働時間を取得
	public static CalculationWorkingTimeTotal workingTimeTotal(Integer employeeId, Integer month,Integer year){
		return CalculationWorkingTimeDao.workingTimeTotal(employeeId, month,year);
	}

	/*企業に所属する各従業員の対象月の労働時間取得*/
	public static void CalculationworkingTime(HttpServletRequest req, HttpServletResponse resp){
		CalculationWorkingTimeLogic.CalculationworkingTime(req,resp);
	}
}
