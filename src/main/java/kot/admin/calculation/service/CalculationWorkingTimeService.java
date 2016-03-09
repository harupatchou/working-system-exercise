package main.java.kot.admin.calculation.service;

import main.java.kot.admin.calculation.CalculationWorkingTimeTotal;
import main.java.kot.dao.WorkingTimeDao;

public class CalculationWorkingTimeService {

	//従業員ID・年月から総労働時間を取得
	public static CalculationWorkingTimeTotal workingTimeTotal(Integer employeeId, Integer month,Integer year){
		return WorkingTimeDao.workingTimeTotal(employeeId, month,year);
	}
}
