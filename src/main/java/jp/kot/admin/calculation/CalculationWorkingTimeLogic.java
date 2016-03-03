package main.java.jp.kot.admin.calculation;

import main.java.jp.kot.admin.dao.WorkingTimeDao;
import main.java.jp.kot.common.WorkingTimeAll;


public class CalculationWorkingTimeLogic {

	//従業員IDから総労働時間を取得
	public static WorkingTimeAll WorkingTimeAll(Integer employeeId){

		WorkingTimeAll workingTimeAll = WorkingTimeDao.WorkingTimeAll(employeeId);
		return workingTimeAll;
	}
}
