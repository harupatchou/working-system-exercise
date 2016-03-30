package main.java.kot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.java.kot.admin.calculation.logic.CalculationWorkingTimeLogic;
import main.java.kot.common.CalculationWorkingTimeTotal;
import main.java.kot.common.SelectDate;
import main.java.kot.common.database.DBCommon;
import main.java.kot.entity.WorkingAll;
import main.java.kot.entity.WorkingDay;
import main.java.kot.logic.ArrayListLogic;
import main.java.kot.logic.DateLogic;
import main.java.kot.logic.HolidayLogic;

public class WorkingAllDao {

	private static String tableName = "working_all";

	/*出退勤情報のインサート*/
	public static boolean insertWorkingAll(WorkingAll workingAll){

		String sql = "INSERT INTO " + tableName + " (date,week,working_time_all,legal_overtime_all,statutory_overtime_all,night_time_all,night_overtime_all,late_time_all,day_status,employee_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement pstmt = DBCommon.getPreparedStatement(sql);

			pstmt.setDate(1,DateLogic.sqlDate(workingAll.getDate()));
			pstmt.setInt(2,workingAll.getWeek());
			pstmt.setString(3,workingAll.getWorkingTimeAll());
			pstmt.setString(4,workingAll.getLegalOvertimeAll());
			pstmt.setString(5,workingAll.getStatutoryOverTimeAll());
			pstmt.setString(6,workingAll.getNightTimeAll());
			pstmt.setString(7,workingAll.getNightOvertimeAll());
			pstmt.setString(8,workingAll.getLateTimeAll());
			pstmt.setString(9,workingAll.getDayStatus());
			pstmt.setInt(10, workingAll.getEmployeeId());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	/*出退勤情報のupdate*/
	public static boolean updateWorkingAll(WorkingAll workingAll) {
		String sql = "UPDATE " + tableName + " SET working_time_all = ?,legal_overtime_all = ?,statutory_overtime_all = ?,night_time_all = ?,night_overtime_all = ?,late_time_all = ? WHERE employee_id = " + workingAll.getEmployeeId() + " AND date = ?";

		try {
			PreparedStatement pstmt = DBCommon.getPreparedStatement(sql);

			Calendar cal = Calendar.getInstance();
			cal.setTime(workingAll.getDate());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());

			pstmt.setString(1,workingAll.getWorkingTimeAll());
			pstmt.setString(2, workingAll.getLegalOvertimeAll());
			pstmt.setString(3, workingAll.getStatutoryOverTimeAll());
			pstmt.setString(4, workingAll.getNightTimeAll());
			pstmt.setString(5, workingAll.getNightOvertimeAll());
			pstmt.setString(6, workingAll.getLateTimeAll());
			pstmt.setDate(7, sqlDate);

			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	//日付とIDから情報取得
	public static WorkingAll selectByDayAndEmployeeId(String day,Integer employeeId) {

		String sql = "select * from " + tableName + " where date = '" + day +"' AND employee_id = " +employeeId;

		try(ResultSet rs = DBCommon.getResultSet(sql);){

			WorkingAll workingAll = new WorkingAll();
			while(rs.next()){
				workingAll.setId(rs.getInt("id"));
				workingAll.setDate(rs.getDate("date"));
				workingAll.setWeek(rs.getInt("week"));
				workingAll.setWorkingTimeAll(rs.getString("working_time_all"));
				workingAll.setLegalOvertimeAll(rs.getString("legal_overtime_all"));
				workingAll.setStatutoryOverTimeAll(rs.getString("statutory_overtime_all"));
				workingAll.setNightOvertimeAll(rs.getString("night_overtime_all"));
				workingAll.setLateTimeAll(rs.getString("late_time_all"));
				workingAll.setDayStatus(rs.getString("day_status"));
				workingAll.setEmployeeId(rs.getInt("employee_id"));
			}
			return workingAll;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
	}

	/*年月・従業員IDから総労働時間取得*/
	public static CalculationWorkingTimeTotal workingTimeTotal(Integer employeeId, Integer month, Integer year){
		String sql = "SELECT DATE_PART('YEAR', date) AS year,DATE_PART('MONTH', date) AS month,working_time_all,legal_overtime_all,statutory_overtime_all,night_time_all,night_overtime_all,day_status FROM " + tableName + " WHERE employee_id = " + employeeId;
		try(ResultSet rs = DBCommon.getResultSet(sql);){

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
			workingTimeTotal.setWorkingTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(workTimeList));
			workingTimeTotal.setLegalOverWorkingTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(legalOverWorkTimeList));
			workingTimeTotal.setStatutoryOverWorkingTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(statutoryOverWorkTimeList));
			workingTimeTotal.setNightTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(nightTimeList));
			workingTimeTotal.setOverNightTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(overNightTimeList));
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
		try(ResultSet rs = DBCommon.getResultSet(sql);){

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
			workingTimeTotal.setWorkingTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(workTimeList));
			workingTimeTotal.setLegalOverWorkingTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(legalOverWorkTimeList));
			workingTimeTotal.setStatutoryOverWorkingTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(statutoryOverWorkTimeList));
			workingTimeTotal.setNightTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(nightTimeList));
			workingTimeTotal.setOverNightTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(overNightTimeList));

			return workingTimeTotal;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}

	}

	/*年月日・曜日・従業員IDから指定週での総労働時間取得*/
	public static CalculationWorkingTimeTotal getCurrentWeeklyWorkingTimeTotal(WorkingDay workingDay, Integer startDay, Integer endDay){
		String sql = "SELECT DATE_PART('YEAR', date) AS year,DATE_PART('MONTH', date) AS month,DATE_PART('DAY', date) AS day,working_time_all,legal_overtime_all,statutory_overtime_all,night_time_all,night_overtime_all FROM " + tableName + " WHERE employee_id = " + workingDay.getEmployeeId();
		try(ResultSet rs = DBCommon.getResultSet(sql);){

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
			workingTimeTotal.setWorkingTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(workTimeList));
			workingTimeTotal.setLegalOverWorkingTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(legalOverWorkTimeList));
			workingTimeTotal.setStatutoryOverWorkingTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(statutoryOverWorkTimeList));
			workingTimeTotal.setNightTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(nightTimeList));
			workingTimeTotal.setOverNightTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(overNightTimeList));

			return workingTimeTotal;


		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
		}

	/*年月を取得*/
	public static SelectDate getYearAndMonth(Integer employeeId){
		String sql = "SELECT DISTINCT DATE_PART('YEAR', date) AS year,DATE_PART('MONTH', date) AS month FROM " + tableName + " WHERE employee_id = " + employeeId;
		try(ResultSet rs = DBCommon.getResultSet(sql);){

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
}
