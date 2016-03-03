package main.java.jp.kot.admin.calculation;

import main.java.jp.kot.admin.dao.WorkingTimeDao;
import main.java.jp.kot.common.WorkingTimeAll;


public class CalculationWorkingTimeLogic {

	//従業員名から総労働時間を取得
	public static WorkingTimeAll WorkingTimeAll(String employeeName){
		WorkingTimeAll workingTimeAll = WorkingTimeDao.WorkingTimeAll(employeeName);
		return workingTimeAll;
	}
}
