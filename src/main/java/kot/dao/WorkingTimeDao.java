package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.kot.admin.calculation.CalculationWorkingTimeTotal;
import main.java.kot.common.database.DBManager;
import main.java.kot.logic.CalculationWorkingTimeLogic;

/**
 *労働時間関連DAO
 * @author ueno
 **/
public class WorkingTimeDao {

	private static String tableName = "working_all";

	/*総労働時間取得*/
	public static CalculationWorkingTimeTotal workingTimeTotal(Integer employeeId, Integer month, Integer year){
		String sql = "SELECT DATE_PART('YEAR', date) AS year,DATE_PART('MONTH', date) AS month,working_time_all,legal_overtime_all,statutory_overtime_all,night_time_all,night_overtime_all FROM " + tableName + " WHERE" + employeeId;
		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){

			//総労働時間格納用
			CalculationWorkingTimeTotal workingTimeTotal = new CalculationWorkingTimeTotal();

			//各要素格納用リスト
			List<String> workTimeList = new ArrayList<>();
			List<String> legalOverWorkTimeList = new ArrayList<>();
			List<String> statutoryOverWorkTimeList = new ArrayList<>();
			List<String> nightTimeList = new ArrayList<>();
			List<String> overNightTimeList = new ArrayList<>();

			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()){
					if(year == rs.getInt("year") && month == rs.getInt("month")){
						workTimeList.add(rs.getString("working_time_all"));
						legalOverWorkTimeList.add(rs.getString("legal_overtime_all"));
						statutoryOverWorkTimeList.add(rs.getString("statutory_overtime_all"));
						nightTimeList.add(rs.getString("night_time_all"));
						overNightTimeList.add(rs.getString("night_overtime_all"));
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

					return workingTimeTotal;

			}

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}

	}
}