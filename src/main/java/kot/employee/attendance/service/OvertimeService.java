package main.java.kot.employee.attendance.service;

import main.java.kot.dao.OvertimeDao;
import main.java.kot.entity.Overtime;

public class OvertimeService {

	//残業時間の送信
		public static boolean insertOvertime(Overtime overtime){
			boolean overtimeJudge = OvertimeDao.getOvertimeFromDailyId(overtime);

			if(overtimeJudge){
				return OvertimeDao.updateOvertime(overtime);
			}else{
				return OvertimeDao.insertOvertime(overtime);
			}
		}

}
