package main.java.kot.employee.overtime;

import main.java.kot.common.Overtime;
import main.java.kot.dao.OvertimeDao;

public class OvertimeService {

	//残業時間の送信
		public static boolean insertWorkingDay(Overtime overtime){
			return OvertimeDao.insertOvertime(overtime);
		}

}
