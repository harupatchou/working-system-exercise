package main.java.kot.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.kot.admin.calculation.WorkingTimeTotal;
import main.java.kot.logic.CalculationWorkingTimeLogic;
import resources.DBManager;

/**
 *労働時間関連DAO
 * @author ueno
 **/
public class WorkingTimeDao {

	private static String tableName = "working_all";

	/*総労働時間取得*/
	public static WorkingTimeTotal workingTimeTotal(Integer employeeId, Integer month, Integer year){
		String sql = "SELECT month,year,working_time_all,legal_overtime_all,non_legal_overtime_all,night_time_all,night_overtime_all FROM " + tableName + " WHERE" + employeeId;
		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){

			//総労働時間格納用
			WorkingTimeTotal workingTimeTotal = new WorkingTimeTotal();

			//各要素格納用リスト
			List<String> workTimeList = new ArrayList<>();
			List<String> legalOverWorkTimeList = new ArrayList<>();
			List<String> nonLegalOverWorkTimeList = new ArrayList<>();
			List<String> nightTimeList = new ArrayList<>();
			List<String> overNightTimeList = new ArrayList<>();

			try(ResultSet rs = pstmt.executeQuery()){
				while(rs.next()){
					if(year == rs.getInt("year") && month == rs.getInt("month")){
						workTimeList.add(rs.getString("working_time_all"));
						legalOverWorkTimeList.add(rs.getString("legal_overtime_all"));
						nonLegalOverWorkTimeList.add(rs.getString("non_legal_overtime_all"));
						nightTimeList.add(rs.getString("night_time_all"));
						overNightTimeList.add(rs.getString("night_overtime_all"));
					}
				}

					//各要素総労働時間算出・格納
					workingTimeTotal.setMonth(month);
					workingTimeTotal.setYear(year);
					workingTimeTotal.setWorkingTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(workTimeList));
					workingTimeTotal.setLegalOverWorkingTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(legalOverWorkTimeList));
					workingTimeTotal.setNonLegalOverWorkingTimeTotal(CalculationWorkingTimeLogic.getWorkTimeTotal(nonLegalOverWorkTimeList));
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