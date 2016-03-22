package main.java.kot.logic;

import java.util.Date;

import main.java.kot.common.CalculationWorkingTimeTotal;
import main.java.kot.common.LimitWorkingTime;
import main.java.kot.common.TempTime;
import main.java.kot.common.workingtime.constant.ConstantWorkingTime;
import main.java.kot.common.workingtime.constant.WeeklyLegalWorkingTime;
import main.java.kot.dao.CalculationWorkingTimeDao;
import main.java.kot.dao.WorkingTimeDao;
import main.java.kot.entity.Employee;
import main.java.kot.entity.Overtime;
import main.java.kot.entity.WorkingDay;
import main.java.kot.entity.WorkingTime;
import main.java.kot.entity.Workingtype;

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

	//月の可能残業時間算出
	public static LimitWorkingTime getPossibleOvertime(Employee employee){

		//現在の総労働時間
		Date date = new Date();
		CalculationWorkingTimeTotal currentCalculationWorkingTimeTotal = CalculationWorkingTimeDao.getCurrentWorkingTimeTotal(employee.getEmployeeId(), date);
		String currentWorkingTimeTotal = currentCalculationWorkingTimeTotal.getWorkingTimeTotal();
		String currentOvertimeTotal = currentCalculationWorkingTimeTotal.getStatutoryOverWorkingTimeTotal();

		//法定労働時間
		String monthlyLegalWorkingTime = WorkingTimeLogic.getMonthlyLegalWorkingtime(date);

		TempTime tempMonthlyLegalWorkingTime = WorkingTimeLogic.getTimeInt(monthlyLegalWorkingTime);
		String monthlyLegalWorkingTimeMessage = tempMonthlyLegalWorkingTime.getHour() + "時間" + tempMonthlyLegalWorkingTime.getMinute() + "分";

		//法定労働時間までの残り時間算出
		String legalWorkingTimeLimitMessage = "";
		TempTime tempLegalWorkingTimeLimit = new TempTime();
		String largeLegalWorkingTime = WorkingTimeLogic.compareWorkingTime(monthlyLegalWorkingTime, currentWorkingTimeTotal);
		if(largeLegalWorkingTime.equals(monthlyLegalWorkingTime)){
			tempLegalWorkingTimeLimit = WorkingTimeLogic.subtractionWorkingTimeTempTime(monthlyLegalWorkingTime, currentWorkingTimeTotal);
		}else{
			tempLegalWorkingTimeLimit = null;
		}

		if(tempLegalWorkingTimeLimit != null){

			String tempHour = String.valueOf(tempLegalWorkingTimeLimit.getHour());
			String tempMinute = "";

			if(tempLegalWorkingTimeLimit.getMinute() < 10){
				tempMinute = "0" + String.valueOf(tempLegalWorkingTimeLimit.getMinute());
			}else{
				tempMinute = String.valueOf(tempLegalWorkingTimeLimit.getMinute());
			}

			legalWorkingTimeLimitMessage = tempHour + "時間" + tempMinute + "分";
		}else{
			legalWorkingTimeLimitMessage = "法定労働時間を超えています";
		}

		//労働上限時間
		String monthlyUpperLimitTime = UpperLimitTimeLogic.getUpperLimitTime(date);
		TempTime tempMonthlyUpperLimitTime =  WorkingTimeLogic.getTimeInt(monthlyUpperLimitTime);
		String monthlyUpperLimitTimeMessage = tempMonthlyUpperLimitTime.getHour() + "時間" + tempMonthlyUpperLimitTime.getMinute() + "分";

		//労働上限チェック
		String workingTimeLimitMessage = UpperLimitTimeLogic.decisionLimitTime(currentWorkingTimeTotal, monthlyUpperLimitTime);

		//残業上限時間
		String overtimeLimit = WorkingTimeLogic.subtractionWorkingTimeString(monthlyUpperLimitTime, monthlyLegalWorkingTime);

		//可能残業時間算出
		String possibleOvertimeMessage = "";
		TempTime tempPossibleOvertime = new TempTime();
		String largeWorkingTime = WorkingTimeLogic.compareWorkingTime(overtimeLimit, currentOvertimeTotal);

		if(largeWorkingTime.equals(overtimeLimit)){
			tempPossibleOvertime = WorkingTimeLogic.subtractionWorkingTimeTempTime(overtimeLimit, currentOvertimeTotal);
		}else{
			tempPossibleOvertime = null;
		}

		if(tempPossibleOvertime != null){

			String tempHour = String.valueOf(tempPossibleOvertime.getHour());
			String tempMinute = "";

			if(tempPossibleOvertime.getMinute() < 10){
				tempMinute = "0" + String.valueOf(tempPossibleOvertime.getMinute());
			}else{
				tempMinute = String.valueOf(tempPossibleOvertime.getMinute());
			}

			possibleOvertimeMessage = tempHour + "時間" + tempMinute + "分";
		}else{
			possibleOvertimeMessage = "残業時間の上限に達しています。これ以上は残業できません。";
		}

		//労働情報格納用
		LimitWorkingTime limitWorkingTime = new LimitWorkingTime();
		limitWorkingTime.setUpperLimitTime(monthlyUpperLimitTimeMessage);
		limitWorkingTime.setMonthlyLegalWorkingTime(monthlyLegalWorkingTimeMessage);
		limitWorkingTime.setOvertimeMessage(possibleOvertimeMessage);
		limitWorkingTime.setMonthlyLegalMessage(legalWorkingTimeLimitMessage);
		limitWorkingTime.setWorkingLimitMessage(workingTimeLimitMessage);

		return limitWorkingTime;
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
		int day = DateLogic.getDate(workingday.getDate()).getDay();

				/*月の残業計算 ここから*/

		// TODO 特例措置企業については考慮していない
		//現時点での今月の総労働時間
		String currentWorkingTimeTotal;//今日も含む
		String untilYesterdayWorkingTimeTotal;//昨日まで
		if(day == 1){
			currentWorkingTimeTotal = workingTime;
			untilYesterdayWorkingTimeTotal = "0:00";
		}else{
			CalculationWorkingTimeTotal currentCalculationWorkingTimeTotal = CalculationWorkingTimeDao.getCurrentWorkingTimeTotal(workingday.getEmployeeId(), workingday.getDate());
			currentWorkingTimeTotal = WorkingTimeLogic.additionWorkingTimeString(currentCalculationWorkingTimeTotal.getWorkingTimeTotal(), workingTime);
			untilYesterdayWorkingTimeTotal = currentCalculationWorkingTimeTotal.getWorkingTimeTotal();
		}

		//月の最大日数から月の法定労働時間をセット
		String monthlyLegalWorkingtime = WorkingTimeLogic.getMonthlyLegalWorkingtime(workingday.getDate());

		//実労働時間が法定労働時間を上回る場合、法定外残業にセットしてリターン
		String largeMonthlyWorkingTime = WorkingTimeLogic.compareWorkingTime(currentWorkingTimeTotal, monthlyLegalWorkingtime);
		String untilYesterdayLargeMonthlyWorkingTime = WorkingTimeLogic.compareWorkingTime(untilYesterdayWorkingTimeTotal, monthlyLegalWorkingtime);

		//本日の労働時間で法定労働時間を超えた場合
		if(largeMonthlyWorkingTime.equals(currentWorkingTimeTotal) && untilYesterdayLargeMonthlyWorkingTime.equals(monthlyLegalWorkingtime)){
			String monthlyOvetime = WorkingTimeLogic.subtractionWorkingTimeString(currentWorkingTimeTotal, monthlyLegalWorkingtime);

			overtime.setLegalOvertime("0:00");
			overtime.setStatutoryOvertime(monthlyOvetime);
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
			return overtime;
		//昨日までに法定労働時間を超えている場合
		}else if(untilYesterdayLargeMonthlyWorkingTime.equals(untilYesterdayWorkingTimeTotal)){
			overtime.setLegalOvertime("0:00");
			overtime.setStatutoryOvertime(workingTime);
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
			return overtime;
		}

				/*月の残業計算 ここまで*/

				/*週の残業計算 ここから*/

		int maxDay = DateLogic.getMaxDay(workingday.getDate());

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
		String untilYesterdayWeeklyWorkingTimeTotal;
		if(day == 1){
			currentWeeklyWorkingTimeTotal = workingTime;
			untilYesterdayWeeklyWorkingTimeTotal = "0:00";
		}else{
			CalculationWorkingTimeTotal currentCalculationWeeklyWorkingTimeTotal = CalculationWorkingTimeDao.getCurrentWeeklyWorkingTimeTotal(workingday, startDay, endDay);
			currentWeeklyWorkingTimeTotal = WorkingTimeLogic.additionWorkingTimeString(currentCalculationWeeklyWorkingTimeTotal.getWorkingTimeTotal(), workingTime);
			untilYesterdayWeeklyWorkingTimeTotal = currentCalculationWeeklyWorkingTimeTotal.getWorkingTimeTotal();
		}

		//週の法定労働時間
		String weeklyLegalWorkingTime = WorkingTimeLogic.getTimeDoubleToString(WeeklyLegalWorkingTime.weeklyLegalWorkingTime);
		String largeWeeklyWorkingTime = WorkingTimeLogic.compareWorkingTime(currentWeeklyWorkingTimeTotal, weeklyLegalWorkingTime);
		String untilYesterdayLargeWeeklyWorkingTime = WorkingTimeLogic.compareWorkingTime(untilYesterdayWeeklyWorkingTimeTotal, weeklyLegalWorkingTime);

		//本日の労働時間で法定労働時間を超えた場合
		if(largeWeeklyWorkingTime.equals(currentWeeklyWorkingTimeTotal) && untilYesterdayLargeWeeklyWorkingTime.equals(weeklyLegalWorkingTime)){
			String weeklyOvertime = WorkingTimeLogic.subtractionWorkingTimeString(currentWeeklyWorkingTimeTotal,weeklyLegalWorkingTime);
			overtime.setLegalOvertime("0:00");
			overtime.setStatutoryOvertime(weeklyOvertime);
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
			return overtime;
		//昨日までに法定労働時間を超えている場合
		}else if(untilYesterdayLargeWeeklyWorkingTime.equals(untilYesterdayWeeklyWorkingTimeTotal)){
			overtime.setLegalOvertime("0:00");
			overtime.setStatutoryOvertime(workingTime);
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
			return overtime;
		}

				/*週の残業計算 ここまで*/

				/*日の残業計算 ここから*/

		//法定労働時間と所定労働時間の差
		Workingtype workingtype = DataLogic.getWorkingtypeFromEmployeeId(workingday.getEmployeeId());
		double timeLag = ConstantWorkingTime.WORKINGTIME - WorkingTimeDao.getWorkingTime(workingtype.getLaborSystemId()).getWorkingTime();
		String timeLagStr = WorkingTimeLogic.getTimeDoubleToString(timeLag);

		//法定内残業・法定外残業セット
		TempTime tempTotalOverTime = WorkingTimeLogic.getTimeInt(totalOvertime);
		TempTime tempLegalOvertime = WorkingTimeLogic.getTimeInt(timeLagStr);
		//法定内で収まらない場合
		if(tempTotalOverTime.getHour() > tempLegalOvertime.getHour() ||
				(tempTotalOverTime.getHour() == tempLegalOvertime.getHour() &&
				tempTotalOverTime.getMinute() > tempLegalOvertime.getMinute())){
			String statutoryOvertime = WorkingTimeLogic.getWorkingtimeLag(WorkingTimeLogic.getTimeInt(timeLagStr),WorkingTimeLogic.getTimeInt(totalOvertime));
			overtime.setLegalOvertime(timeLagStr);
			overtime.setStatutoryOvertime(statutoryOvertime);
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
		//法定内で収まる場合
		}else if(tempTotalOverTime.getHour() == tempLegalOvertime.getHour() &&
				tempTotalOverTime.getMinute() > 0 &&
				tempTotalOverTime.getMinute() < tempLegalOvertime.getMinute()){
			overtime.setLegalOvertime(totalOvertime);
			overtime.setStatutoryOvertime("0:00");
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
		//残業なし
		}else{
			overtime.setLegalOvertime("0:00");
			overtime.setStatutoryOvertime("0:00");
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
		}

				/*日の残業計算 ここまで*/

		return overtime;
	}


	/*フレックスタイム制残業計算*/
	public static Overtime getFlexTimeOvertime(WorkingDay workingday){

		//残業情報格納用(dailyIDはDBインサート直前でセット)
		Overtime overtime = new Overtime();

		//入力日の実労働時間
		String todayWorkingTime = getWorkingTime(workingday);

		//日付
		int day = DateLogic.getDate(workingday.getDate()).getDay();
		int maxDay = DateLogic.getMaxDay(workingday.getDate());

		//今月の総労働時間
		String currentWorkingTimeTotal;//今日も含む
		String untilYesterdayWorkingTimeTotal;//昨日まで
		if(day == 1){
			currentWorkingTimeTotal = todayWorkingTime;
			untilYesterdayWorkingTimeTotal = "0:00";
		}else{
			CalculationWorkingTimeTotal currentCalculationWorkingTimeTotal = CalculationWorkingTimeDao.getCurrentWorkingTimeTotal(workingday.getEmployeeId(), workingday.getDate());
			currentWorkingTimeTotal = WorkingTimeLogic.additionWorkingTimeString(currentCalculationWorkingTimeTotal.getWorkingTimeTotal(), todayWorkingTime);
			untilYesterdayWorkingTimeTotal = currentCalculationWorkingTimeTotal.getWorkingTimeTotal();
		}

		//月の最大日数から月の法定労働時間をセット
		Workingtype workingtype = DataLogic.getWorkingtypeFromEmployeeId(workingday.getEmployeeId());
		WorkingTime workingTime = WorkingTimeDao.getWorkingTime(workingtype.getLaborSystemId());

		String monthlyLegalWorkingtime;
		if(workingTime.getCarryoverTime().equals("0:00")){
			monthlyLegalWorkingtime = WorkingTimeLogic.getMonthlyLegalWorkingtime(workingday.getDate());
		}else{
			monthlyLegalWorkingtime = WorkingTimeLogic.additionWorkingTimeString(WorkingTimeLogic.getMonthlyLegalWorkingtime(workingday.getDate()), workingTime.getCarryoverTime());
		}

		//残業時間をセット
		String currentLargeWorkingTime = WorkingTimeLogic.compareWorkingTime(currentWorkingTimeTotal, monthlyLegalWorkingtime);
		String untilYesterdayLargeWorkingTime = WorkingTimeLogic.compareWorkingTime(untilYesterdayWorkingTimeTotal, monthlyLegalWorkingtime);
		//法定労働時間を超えていない場合
		if(currentLargeWorkingTime.equals(monthlyLegalWorkingtime)){
			//最終日に法定時間に達しなかった場合、翌月に持越し
			if(day == maxDay){
				String carryoverWorkingTime = WorkingTimeLogic.subtractionWorkingTimeString(monthlyLegalWorkingtime, currentWorkingTimeTotal);
				WorkingTimeDao.updateCarryOverTime(workingTime.getWorkingTimeId(), carryoverWorkingTime);
			}
			overtime.setLegalOvertime("0:00");
			overtime.setStatutoryOvertime("0:00");
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
		//昨日までの総労働時間が法定労働時間を超えていない場合
		}else if(currentLargeWorkingTime.equals(currentWorkingTimeTotal) && untilYesterdayLargeWorkingTime.equals(monthlyLegalWorkingtime)){
			String statutoryOvertime = WorkingTimeLogic.subtractionWorkingTimeString(currentWorkingTimeTotal, monthlyLegalWorkingtime);
			overtime.setLegalOvertime("0:00");
			overtime.setStatutoryOvertime(statutoryOvertime);
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
		//昨日までで法定労働時間を超えている場合
		}else{
			overtime.setLegalOvertime("0:00");
			overtime.setStatutoryOvertime(todayWorkingTime);
			overtime.setNightOvertime("0:00");
			overtime.setStatutoryNightOvertime("0:00");
		}
		return overtime;
	}

	/* TODO 早出残業用メソッド(現在は考慮しないで良い) */
	public static String getEarlyAttendanceOvertime(){
		return null;
	}

	/* TODO みなし労働時間制用メソッド*/
	public static String discretionaryWorkingHourSystemOvertime(){
		/*
		//現在まで総労働時間にみなし労働時間を加算


		//労働時間
		String workingTime = WorkingTimeLogic.getStandardsWorkingTimeFromWorkingtype(employee.getWorkingTypeId());

		String considersLabor = "";
		for(int i = 0;i <= maxDay -day;i++){
			cal.add(Calendar.DAY_OF_MONTH, 1);
			int week = cal.get(Calendar.DAY_OF_WEEK);
			if(cal.get(Calendar.DAY_OF_WEEK) != 1 && cal.get(Calendar.DAY_OF_WEEK) != 7){
				if(considersLabor.equals("")){
					considersLabor = WorkingTimeLogic.additionWorkingTimeString(workingTime, currentWorkingTimeTotal);
				}else{
					considersLabor = WorkingTimeLogic.additionWorkingTimeString(workingTime, considersLabor);
				}
			}
		}*/
		return null;
	}
}
