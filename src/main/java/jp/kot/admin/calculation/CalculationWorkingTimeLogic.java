package main.java.jp.kot.admin.calculation;

import main.java.jp.kot.common.WorkingTimeAll;
import main.java.jp.kot.common.WorkingTimeDao;


public class CalculationWorkingTimeLogic {

	//従業員名から総労働時間を取得
	public static Integer WorkingTimeAll(String employeeName){
		WorkingTimeAll workingTimeAll = WorkingTimeDao.WorkingTimeAll(employeeName);
		return workingTimeAll.getWorkingTimeAll();
	}
}