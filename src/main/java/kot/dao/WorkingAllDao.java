package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import main.java.kot.common.database.DBManager;
import main.java.kot.entity.WorkingAll;

public class WorkingAllDao {

	private static String tableName = "working_all";

	/*出退勤情報のインサート*/
	public static boolean insertWorkingAll(WorkingAll workingAll){

		String sql = "INSERT INTO " + tableName + " (date,week,working_time_all,legal_overtime_all,statutory_overtime_all,night_time_all,night_overtime_all,late_time_all,day_status,employee_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);

			Calendar cal = Calendar.getInstance();
			cal.setTime(workingAll.getDate());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());

			//TODO 微妙
			pstmt.setDate(1,sqlDate);
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
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);

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

		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){

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
}
