package main.java.kot.logic;

import java.util.Calendar;

import main.java.kot.common.CalculationWorkingTimeTotal;
import main.java.kot.common.TempTime;
import main.java.kot.common.workingtime.constant.ConstantWorkingTime;
import main.java.kot.common.workingtime.constant.WeeklyLegalWorkingTime;
import main.java.kot.dao.WorkingTimeDao;
import main.java.kot.entity.Overtime;
import main.java.kot.entity.WorkingDay;

/**
 * 残業関連ロジッククラス
 * @author ueno
 **/
public class OvertimeLogic {

	//実労働時間取得
	public static String getWorkingTime(WorkingDay workingday){

		String[] startTimeStr = DateLogic.timeStr(workingday.getAttendanceTime());
		String[] endTimeStr = DateLogic.timeStr(workingday.getLeaveTime());

		//一日の総労働時間算出
		String attendDayAll = DateLogic.getStringTime(startTimeStr,endTimeStr);

		//一日の実労働時間算出
		String attendDay= DateLogic.getCalculateTime(attendDayAll,workingday.getNapTime());

		return attendDay;
	}


	/*実労働時間を法定外残業にセット(土日用処理)*/
	public static Overtime getOvertimeOnSaturdayAndSunday(WorkingDay workingday){

		Overtime overtime = new Overtime();

		//一日の実労働時間算出
		String attendDay= getWorkingTime(workingday);

		overtime.setLegalOvertime("0:00");
		overtime.setStatutoryOvertime(attendDay);
		overtime.setNightOvertime("0:00");
		overtime.setStatutoryNightOvertime("0:00");

		return overtime;
	}

	/*通常残業時間*/
	public static Overtime getOvertime(WorkingDay workingday){

		/*dailyIDはDBインサート直前でセット*/
		Overtime overtime = new Overtime();

		//土日用処理
		if(workingday.getWeek() == 7 || workingday.getWeek() == 1){
			overtime = getOvertimeOnSaturdayAndSunday(workingday);
			return overtime;
		}

		//対象従業員種別の終業時刻
		String workingtypeEndtime = DataLogic.getAttendanceTimeFromEmployeeId(workingday.getEmployeeId()).getEndTime();
		TempTime startOvertime = WorkingTimeLogic.getTimeInt(workingtypeEndtime);

		//退勤時刻
		TempTime endOvertime = WorkingTimeLogic.getTimeInt(workingday.getLeaveTime());

		String tempOvertime = WorkingTimeLogic.getWorkingtimeLag(startOvertime, endOvertime);

		overtime.setLegalOvertime("0:00");
		overtime.setStatutoryOvertime(tempOvertime);
		overtime.setNightOvertime("0:00");
		overtime.setStatutoryNightOvertime("0:00");
		return overtime;
	}


	/*変形労働制残業時間(一ヶ月単位)*/
	public static Overtime getIrregularWorkingHourSystemOvertime(WorkingDay workingday){
		//残業情報格納用(dailyIDはDBインサート直前でセット)
		Overtime overtime = new Overtime();

		//土日用処理
		if(workingday.getWeek() == 7 || workingday.getWeek() == 1){
			overtime = getOvertimeOnSaturdayAndSunday(workingday);
			return overtime;
		}

		//入力日の実労働時間
		String workingTime = getWorkingTime(workingday);

		//対象従業員種別の終業時刻
		String workingtypeEndtime = DataLogic.getAttendanceTimeFromEmployeeId(workingday.getEmployeeId()).getEndTime();
		TempTime startOvertime = WorkingTimeLogic.getTimeInt(workingtypeEndtime);

		//退勤時刻
		TempTime endOvertime = WorkingTimeLogic.getTimeInt(workingday.getLeaveTime());

		//残業時間
		String totalOvertime = WorkingTimeLogic.getWorkingtimeLag(startOvertime, endOvertime);


		//日付
		Calendar cal = Calendar.getInstance();
		cal.setTime(workingday.getDate());
		int day = cal.get(Calendar.DAY_OF_MONTH);

				/*月の残業計算 ここから*/

		// TODO 特例措置企業については考慮していない
		//現時点での今月の総労働時間
		String currentWorkingTimeTotal;
		if(day == 1){
			currentWorkingTimeTotal = workingTime;
		}else{
			CalculationWorkingTimeTotal currentCalculationWorkingTimeTotal = WorkingTimeDao.getCurrentWorkingTimeTotal(workingday.getEmployeeId(), workingday.getDate());
			currentWorkingTimeTotal = WorkingTimeLogic.additionWorkingTimeString(currentCalculationWorkingTimeTotal.getWorkingTimeTotal(), workingTime);
		}

		//月の最大日数から月の法定労働時間をセット
		String monthlyLegalWorkingtime = WorkingTimeLogic.getMonthlyLegalWorkingtime(workingday.getDate());

		//実労働時間が法定労働時間を上回る場合、法定外残業にセットしてリターン
		String largeMonthlyWorkingTime = WorkingTimeLogic.compareWorkingTime(currentWorkingTimeTotal, monthlyLegalWorkingtime);
		if(largeMonthlyWorkingTime.equals(currentWorkingTimeTotal)){
			String monthlyOvetime = WorkingTimeLogic.subtractionWorkingTimeString(currentWorkingTimeTotal, monthlyLegalWorkingtime);

			overtime.setLegalOvertime("0:00");
			overtime.setStatutoryOvertime(monthlyOvetime);
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
			return overtime;
		}

				/*月の残業計算 ここまで*/

				/*週の残業計算 ここから*/

		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		int startDay = 0;
		int endDay = day;

		//金曜日に週の労働時間と法定労働時間から残業計算を行う
		if(workingday.getWeek() == 6){
			//第一週
			if(day <= 5){
				startDay = 1;
			}else if(day > 5 && day < maxDay){
				startDay = day - 6;
			}
		//金曜日以外の場合
		}else if(workingday.getWeek() != 6){
			startDay = day - workingday.getWeek() + 2;//土曜から今日までの期間(曜日が1～7までのint型で判定なので金曜日との差を出すために+2)
		}

		//今週の実労働時間
		String currentWeeklyWorkingTimeTotal;
		if(day == 1){
			currentWeeklyWorkingTimeTotal = workingTime;
		}else{
			CalculationWorkingTimeTotal currentCalculationWeeklyWorkingTimeTotal = WorkingTimeDao.getCurrentWeeklyWorkingTimeTotal(workingday, startDay, endDay);
			currentWeeklyWorkingTimeTotal = WorkingTimeLogic.additionWorkingTimeString(currentCalculationWeeklyWorkingTimeTotal.getWorkingTimeTotal(), workingTime);
		}

		//週の法定労働時間
		String weeklyLegalWorkingTime = WorkingTimeLogic.getTimeDoubleToString(WeeklyLegalWorkingTime.weeklyLegalWorkingTime);
		String largeWeeklyWorkingTime = WorkingTimeLogic.compareWorkingTime(currentWeeklyWorkingTimeTotal, weeklyLegalWorkingTime);

		if(largeWeeklyWorkingTime.equals(currentWeeklyWorkingTimeTotal)){
			String weeklyOvertime = WorkingTimeLogic.subtractionWorkingTimeString(currentWeeklyWorkingTimeTotal,weeklyLegalWorkingTime);
			overtime.setLegalOvertime("0:00");
			overtime.setStatutoryOvertime(weeklyOvertime);
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
			return overtime;
		}

				/*週の残業計算 ここまで*/

				/*日の残業計算 ここから*/

		//法定労働時間と所定労働時間の差
		double timeLag = ConstantWorkingTime.WORKINGTIME - ConstantWorkingTime.IRREGULARWORKINGTIME;
		String timeLagStr = WorkingTimeLogic.getTimeDoubleToString(timeLag);

		//法定内残業・法定外残業セット
		TempTime tempTotalOverTime = WorkingTimeLogic.getTimeInt(totalOvertime);
		TempTime tempStatutoryOvertime = WorkingTimeLogic.getTimeInt(timeLagStr);
		//法定内で収まらない場合
		if(tempTotalOverTime.getHour() > tempStatutoryOvertime.getHour() ||
				(tempTotalOverTime.getHour() == tempStatutoryOvertime.getHour() &&
				tempTotalOverTime.getMinute() > tempStatutoryOvertime.getMinute())){
			String statutoryOvertime = WorkingTimeLogic.getWorkingtimeLag(WorkingTimeLogic.getTimeInt(timeLagStr),WorkingTimeLogic.getTimeInt(totalOvertime));
			overtime.setLegalOvertime(timeLagStr);
			overtime.setStatutoryOvertime(statutoryOvertime);
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
		//法定内で収まる場合
		}else if(tempTotalOverTime.getHour() == tempStatutoryOvertime.getHour() &&
				tempTotalOverTime.getMinute() > 0 &&
				tempTotalOverTime.getMinute() < tempStatutoryOvertime.getMinute()){
			overtime.setLegalOvertime(totalOvertime);
			overtime.setStatutoryOvertime("0:00");
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
		}

				/*日の残業計算 ここまで*/

		return overtime;
	}

	/* TODO 早出残業用メソッド(現在は考慮しないで良い) */
	public static String getEarlyAttendanceOvertime(){
		return null;
	}
}
