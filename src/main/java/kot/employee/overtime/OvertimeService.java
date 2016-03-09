package main.java.kot.employee.overtime;

import main.java.kot.dao.OvertimeDao;
import main.java.kot.entity.Overtime;

public class OvertimeService {

	//残業時間の送信
		public static boolean insertWorkingDay(Overtime overtime){
			return OvertimeDao.insertOvertime(overtime);
		}

}
