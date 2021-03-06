package main.java.working.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.working.common.database.DBCommon;
import main.java.working.entity.LaborSystem;
import main.java.working.entity.WorkingTime;

public class WorkingTimeDao {

	private static String tableName = "working_time";

	/*労働制IDから労働時間情報取得*/
	public static WorkingTime getWorkingTime(Integer laborSystemId){
		String sql = "SELECT work.*, labor.* FROM " + tableName + " work JOIN labor_system labor ON work.labor_system_id = labor.id WHERE labor_system_id = " + laborSystemId;
		try(ResultSet rs = DBCommon.getResultSet(sql);){

			WorkingTime workingTime = new WorkingTime();
			while(rs.next()){
				workingTime.setWorkingTimeId(rs.getInt("id"));
				workingTime.setWorkingTime(rs.getDouble("working_time"));
				workingTime.setCarryoverTime(rs.getString("carryover_time"));

				LaborSystem tempLabor = new LaborSystem();
				tempLabor.setId(rs.getInt("labor_system_id"));
				tempLabor.setLaborSystemName(rs.getString("labor_system_name"));
				workingTime.setLaborSystem(tempLabor);

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
			PreparedStatement pstmt = DBCommon.getPreparedStatement(sql);

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
		String sql = "UPDATE " + tableName + " SET working_time = ? WHERE labor_system_id = " + workingTime.getLaborSystem().getId();

		try {
			PreparedStatement pstmt = DBCommon.getPreparedStatement(sql);

			pstmt.setDouble(1,workingTime.getWorkingTime());

			pstmt.executeUpdate();

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}
}
