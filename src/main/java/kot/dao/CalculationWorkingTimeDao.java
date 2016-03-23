package main.java.kot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.java.kot.common.CalculationWorkingTimeTotal;
import main.java.kot.common.SelectDate;
import main.java.kot.common.database.DBcommon;
import main.java.kot.entity.WorkingDay;
import main.java.kot.logic.ArrayListLogic;
import main.java.kot.logic.HolidayLogic;

/**
 *労働時間関連DAO
 * @author ueno
 **/
public class CalculationWorkingTimeDao {

	private static String tableName = "working_all";

	/*年月・従業員IDから総労働時間取得*/
	public static CalculationWorkingTimeTotal workingTimeTotal(Integer employeeId, Integer month, Integer year){
		String sql = "SELECT DATE_PART('YEAR', date) AS year,DATE_PART('MONTH', date) AS month,working_time_all,legal_overtime_all,statutory_overtime_all,night_time_all,night_overtime_all,day_status FROM " + tableName + " WHERE employee_id = " + employeeId;
		try(ResultSet rs = DBcommon.getResultSet(sql);){

			//総労働時間格納用
			CalculationWorkingTimeTotal workingTimeTotal = new CalculationWorkingTimeTotal();

			//各要素格納用リスト
			List<String> workTimeList = new ArrayList<>();
			List<String> legalOverWorkTimeList = new ArrayList<>();
			List<String> statutoryOverWorkTimeList = new ArrayList<>();
			List<String> nightTimeList = new ArrayList<>();
			List<String> overNightTimeList = new ArrayList<>();
			List<String> dayStatus = new ArrayList<>();

			while(rs.next()){
				if(year == rs.getInt("year") && month == rs.getInt("month")){
					workTimeList.add(rs.getString("working_time_all"));
					legalOverWorkTimeList.add(rs.getString("legal_overtime_all"));
					statutoryOverWorkTimeList.add(rs.getString("statutory_overtime_all"));
					nightTimeList.add(rs.getString("night_time_all"));
					overNightTimeList.add(rs.getString("night_overtime_all"));
					dayStatus.add(rs.getString("day_status"));
				}
			}

			//各要素総労働時間算出・格納
			workingTimeTotal.setMonth(month);
			workingTimeTotal.setYear(year);
			workingTimeTotal.setWorkingTimeTotal(getWorkTimeTotal(workTimeList));
			workingTimeTotal.setLegalOverWorkingTimeTotal(getWorkTimeTotal(legalOverWorkTimeList));
			workingTimeTotal.setStatutoryOverWorkingTimeTotal(getWorkTimeTotal(statutoryOverWorkTimeList));
			workingTimeTotal.setNightTimeTotal(getWorkTimeTotal(nightTimeList));
			workingTimeTotal.setOverNightTimeTotal(getWorkTimeTotal(overNightTimeList));
			workingTimeTotal.setHolidayTimeTotal(HolidayLogic.getHolidayTime(dayStatus,workTimeList));

			return workingTimeTotal;


		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}

	}

	/*年月・従業員IDから現時点での総労働時間取得*/
	public static CalculationWorkingTimeTotal getCurrentWorkingTimeTotal(Integer employeeId, Date date){
		String sql = "SELECT DATE_PART('YEAR', date) AS year,DATE_PART('MONTH', date) AS month,DATE_PART('DAY', date) AS day,working_time_all,legal_overtime_all,statutory_overtime_all,night_time_all,night_overtime_all FROM " + tableName + " WHERE employee_id = " + employeeId;
		try(ResultSet rs = DBcommon.getResultSet(sql);){

			//総労働時間格納用
			CalculationWorkingTimeTotal workingTimeTotal = new CalculationWorkingTimeTotal();

			//各要素格納用リスト
			List<String> workTimeList = new ArrayList<>();
			List<String> legalOverWorkTimeList = new ArrayList<>();
			List<String> statutoryOverWorkTimeList = new ArrayList<>();
			List<String> nightTimeList = new ArrayList<>();
			List<String> overNightTimeList = new ArrayList<>();

			/* TODO Date型比較だとうまくいかなかったので一旦年月日で分けて比較*/
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;

			while(rs.next()){
				int tempyear = rs.getInt("year");
				int tempmonth = rs.getInt("month");
				if(year == tempyear && month == tempmonth){
					workTimeList.add(rs.getString("working_time_all"));
					legalOverWorkTimeList.add(rs.getString("legal_overtime_all"));
					statutoryOverWorkTimeList.add(rs.getString("statutory_overtime_all"));
					nightTimeList.add(rs.getString("night_time_all"));
					overNightTimeList.add(rs.getString("night_overtime_all"));
				}
			}

			//各要素総労働時間算出・格納
			workingTimeTotal.setWorkingTimeTotal(getWorkTimeTotal(workTimeList));
			workingTimeTotal.setLegalOverWorkingTimeTotal(getWorkTimeTotal(legalOverWorkTimeList));
			workingTimeTotal.setStatutoryOverWorkingTimeTotal(getWorkTimeTotal(statutoryOverWorkTimeList));
			workingTimeTotal.setNightTimeTotal(getWorkTimeTotal(nightTimeList));
			workingTimeTotal.setOverNightTimeTotal(getWorkTimeTotal(overNightTimeList));

			return workingTimeTotal;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}

	}

	/*年月日・曜日・従業員IDから指定週での総労働時間取得*/
	public static CalculationWorkingTimeTotal getCurrentWeeklyWorkingTimeTotal(WorkingDay workingDay, Integer startDay, Integer endDay){
		String sql = "SELECT DATE_PART('YEAR', date) AS year,DATE_PART('MONTH', date) AS month,DATE_PART('DAY', date) AS day,working_time_all,legal_overtime_all,statutory_overtime_all,night_time_all,night_overtime_all FROM " + tableName + " WHERE employee_id = " + workingDay.getEmployeeId();
		try(ResultSet rs = DBcommon.getResultSet(sql);){

			//総労働時間格納用
			CalculationWorkingTimeTotal workingTimeTotal = new CalculationWorkingTimeTotal();

			//各要素格納用リスト
			List<String> workTimeList = new ArrayList<>();
			List<String> legalOverWorkTimeList = new ArrayList<>();
			List<String> statutoryOverWorkTimeList = new ArrayList<>();
			List<String> nightTimeList = new ArrayList<>();
			List<String> overNightTimeList = new ArrayList<>();

			/* TODO Date型比較だとうまくいかなかったので一旦年月日で分けて比較*/
			Calendar cal = Calendar.getInstance();
			cal.setTime(workingDay.getDate());
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH) + 1;

			while(rs.next()){
				int tempyear = rs.getInt("year");
				int tempmonth = rs.getInt("month");
				int tempday = rs.getInt("day");
				if(year == tempyear && month == tempmonth && startDay <= tempday && endDay >= tempday){
					workTimeList.add(rs.getString("working_time_all"));
					legalOverWorkTimeList.add(rs.getString("legal_overtime_all"));
					statutoryOverWorkTimeList.add(rs.getString("statutory_overtime_all"));
					nightTimeList.add(rs.getString("night_time_all"));
					overNightTimeList.add(rs.getString("night_overtime_all"));
				}
			}

			//各要素総労働時間算出・格納
			workingTimeTotal.setWorkingTimeTotal(getWorkTimeTotal(workTimeList));
			workingTimeTotal.setLegalOverWorkingTimeTotal(getWorkTimeTotal(legalOverWorkTimeList));
			workingTimeTotal.setStatutoryOverWorkingTimeTotal(getWorkTimeTotal(statutoryOverWorkTimeList));
			workingTimeTotal.setNightTimeTotal(getWorkTimeTotal(nightTimeList));
			workingTimeTotal.setOverNightTimeTotal(getWorkTimeTotal(overNightTimeList));

			return workingTimeTotal;


		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
		}

	/*年月を取得*/
	public static SelectDate getYearAndMonth(Integer employeeId){
		String sql = "SELECT DATE_PART('YEAR', date) AS year,DATE_PART('MONTH', date) AS month FROM " + tableName + " WHERE employee_id = " + employeeId;
		try(ResultSet rs = DBcommon.getResultSet(sql);){

			SelectDate selectDate = new SelectDate();

			List<Integer> yearList = new ArrayList<>();
			List<Integer> monthList = new ArrayList<>();

			while(rs.next()){
				yearList.add(rs.getInt("year"));
				monthList.add(rs.getInt("month"));
			}
			//重複削除してセット
			selectDate.setYearList(ArrayListLogic.duplicateDelete(yearList));
			selectDate.setMonthList(ArrayListLogic.duplicateDelete(monthList));
			return selectDate;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
	}

	/*総労働時間算出*/
	private static String getWorkTimeTotal(List<String> timeList){

		//時分格納用リスト
		List<Integer> HourList = new ArrayList<>();
		List<Integer> MinuteList =new ArrayList<>();

		for (int i = 0; i < timeList.size(); i++) {
			String[] temptimeList = timeList.get(i).split(":");
			HourList.add(Integer.parseInt(temptimeList[0]));
			MinuteList.add(Integer.parseInt(temptimeList[1]));
		}

		//総労働時間算出
		int HourTotal = 0;
		for(int i = 0; i < HourList.size(); i++){
			HourTotal += HourList.get(i);
		}
		int MinuteTotal = 0;
		for (int i = 0; i < MinuteList.size(); i++) {
			MinuteTotal += MinuteList.get(i);
		}
		HourTotal += MinuteTotal / 60;
		MinuteTotal = MinuteTotal % 60;

		//時分をString型に変更
		String tempHourTotal = String.valueOf(HourTotal);
		String tempMinuteTotal;
		//分が一桁の場合は先頭に0を付与
		if(MinuteTotal < 10){
			tempMinuteTotal = "0" + String.valueOf(MinuteTotal);
		}else{
			tempMinuteTotal = String.valueOf(MinuteTotal);
		}

		String timeTotal = tempHourTotal + ":" + tempMinuteTotal;

		return timeTotal;
	}
}