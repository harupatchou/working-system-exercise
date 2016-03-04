package main.java.jp.kot.admin.logic;

import main.java.jp.kot.admin.dao.WorkingTimeDao;
import main.java.jp.kot.common.WorkingTimeTotal;


public class CalculationWorkingTimeLogic {

	//従業員IDから総労働時間を取得
	public static WorkingTimeTotal workingTimeTotal(Integer employeeId){

		WorkingTimeTotal workingTimeAll = WorkingTimeDao.workingTimeTotal(employeeId);
		return workingTimeAll;
	}
}
