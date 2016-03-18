package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.kot.common.database.DBManager;
import main.java.kot.entity.WorkingTime;

public class WorkingTimeDao {

	private static String tableName = "working_time";

	/*労働制IDから労働時間情報取得*/
	public static WorkingTime getWorkingTime(Integer laborSystemId){
		String sql = "SELECT * FROM " + tableName + " WHERE labor_system_id = " + laborSystemId;
		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){

			WorkingTime workingTime = new WorkingTime();
			while(rs.next()){
				workingTime.setWorkingTimeId(rs.getInt("id"));
				workingTime.setWorkingTime(rs.getDouble("working_time"));
				workingTime.setCarryoverTime(rs.getString("carryover_time"));
				workingTime.setLaborSystemId(rs.getInt("labor_system_id"));
			}
			return workingTime;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
	}

	/*持越し時間アップデート*/
	public static boolean updateCarryOverTime(Integer workingTimeId, String carryoverTime){

		String sql = "UPDATE " + tableName + " SET carryover_time = ? WHERE working_type_id = " + workingTimeId;

		try {
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1,carryoverTime);

			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	//update
	public static void editWorkingTime(WorkingTime workingTime) {
		String sql = "UPDATE " + tableName + " SET working_time = ? WHERE labor_system_id = " + workingTime.getLaborSystemId();

		try {
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setDouble(1,workingTime.getWorkingTime());

			pstmt.executeUpdate();

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}
}
