package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import main.java.kot.common.database.DBManager;
import main.java.kot.entity.WorkingDay;


public class WorkingDayDao {

	private static String tableName = "working_day";

	/*出退勤情報のインサート*/
	public static boolean insertWorkingDay(WorkingDay workingDay){

		String sql = "INSERT INTO " + tableName + " (date,week,attendance_time,leave_time,break_time,nap_time,employee_id,legal_flag) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);

			Calendar cal = Calendar.getInstance();
			cal.setTime(workingDay.getDate());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());

			//TODO 微妙
			pstmt.setDate(1,sqlDate);
			pstmt.setInt(2,workingDay.getWeek());
			pstmt.setString(3, workingDay.getAttendanceTime());
			pstmt.setString(4, workingDay.getLeaveTime());
			pstmt.setString(5, workingDay.getBreakTime());
			pstmt.setString(6, workingDay.getNapTime());
			pstmt.setInt(7, workingDay.getEmployeeId());
			pstmt.setInt(8, workingDay.getLegalFlag());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	public static WorkingDay selectByDayAndEmployeeId(String selectDay,Integer employeeId) {

		String sql = "select * from " + tableName + " where date = '" + selectDay +"' AND employee_id = " +employeeId;

		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){

			WorkingDay workingDay = new WorkingDay();
			while(rs.next()){
				workingDay.setId(rs.getInt("id"));
				workingDay.setWeek(rs.getInt("week"));
				workingDay.setAttendanceTime(rs.getString("attendance_time"));
				workingDay.setLeaveTime(rs.getString("leave_time"));
				workingDay.setBreakTime(rs.getString("break_time"));
				workingDay.setNapTime(rs.getString("nap_time"));
				workingDay.setEmployeeId(rs.getInt("employee_id"));
				workingDay.setLegalFlag(rs.getInt("legal_flag"));
			}
			return workingDay;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
	}

	//年月、従業員IDから情報取得
	public static WorkingDay selectAllByEmployeeId(Integer year, Integer month,Integer day, Integer userId) {
		String sql = "SELECT DATE_PART('YEAR', date) AS year,DATE_PART('MONTH', date) AS month,DATE_PART('DAY', date) AS day,id,week,attendance_time,leave_time,break_time,nap_time,legal_flag FROM " + tableName + " WHERE employee_id = " + userId;

		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();){

		WorkingDay workingDay = new WorkingDay();
		while(rs.next()){
			if(year == rs.getInt("year") && month == rs.getInt("month") && day == rs.getInt("day")){
				workingDay.setId(rs.getInt("id"));
				workingDay.setWeek(rs.getInt("week"));
				workingDay.setAttendanceTime(rs.getString("attendance_time"));
				workingDay.setLeaveTime(rs.getString("leave_time"));
				workingDay.setBreakTime(rs.getString("break_time"));
				workingDay.setNapTime(rs.getString("nap_time"));
				workingDay.setLegalFlag(rs.getInt("legal_flag"));
			}
		}
		return workingDay;

		}catch(SQLException e){
			WorkingDay workingDay = new WorkingDay();
			return workingDay;
		}
	}


}
