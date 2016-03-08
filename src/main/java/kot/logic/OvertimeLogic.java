package main.java.kot.logic;

import main.java.kot.common.Overtime;
import main.java.kot.common.TempTime;
import main.java.kot.common.WorkingDay;

/**
 * 残業関連ロジッククラス
 * @author ueno
 **/
public class OvertimeLogic {


	/*通常残業時間*/
	//実労働時間から残業情報を取得
	public static Overtime getOvertime(WorkingDay workingday){

		//対象従業員種別の終業時刻
		String workingtypeEndtime = GetDataLogic.getAttendanceTimeFromEmployeeId(workingday.getEmployeeId()).getEnd_time();
		TempTime startOvertime = DateLogic.getTimeInt(DateLogic.timeStr(workingtypeEndtime));

		//退勤時刻
		TempTime endOvertime = DateLogic.getTimeInt(DateLogic.timeStr(workingday.getLeaveTime()));

		String tempOvertime = DateLogic.getWorkingtimeLag(startOvertime, endOvertime);

		/* TODO dailyIDは後ほど入れる*/
		Overtime overtime = new Overtime();
		overtime.setLegalOvertime("0:00");
		overtime.setNonLeagalOvertime(tempOvertime);
		return overtime;
	}



	/* TODO 早出残業用メソッド(現在は考慮しないで良い) */
	public static String getEarlyAttendanceOvertime(){
		return null;
	}
}
