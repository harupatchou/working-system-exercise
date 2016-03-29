package main.java.kot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import main.java.kot.common.database.DBCommon;
import main.java.kot.entity.AttendanceStatus;
import main.java.kot.entity.WorkingDay;


public class WorkingDayDao {

	private static String tableName = "working_day";

	/*出退勤情報のインサート*/
	public static boolean insertWorkingDay(WorkingDay workingDay){

		String sql = "INSERT INTO " + tableName + " (date,week,attendance_time,leave_time,break_time_start,break_time_end,nap_time,employee_id,legal_flag,attendance_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement pstmt = DBCommon.getPreparedStatement(sql);

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
			pstmt.setString(5, workingDay.getBreakTimeStart());
			pstmt.setString(6, workingDay.getBreakTimeEnd());
			pstmt.setString(7, workingDay.getNapTime());
			pstmt.setInt(8, workingDay.getEmployeeId());
			pstmt.setInt(9, workingDay.getLegalFlag());
			pstmt.setInt(10, workingDay.getStatusCode());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	/*出退勤情報のupdate*/
	public static boolean updateWorkingDay(WorkingDay workingDay) {
		String sql = "UPDATE " + tableName + " SET attendance_time = ?,leave_time = ?,break_time_start = ?,break_time_end = ?,nap_time = ?,attendance_status = ? WHERE employee_id = " + workingDay.getEmployeeId() + " AND date = ?";

		try {
			PreparedStatement pstmt = DBCommon.getPreparedStatement(sql);

			Calendar cal = Calendar.getInstance();
			cal.setTime(workingDay.getDate());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());

			pstmt.setString(1,workingDay.getAttendanceTime());
			pstmt.setString(2, workingDay.getLeaveTime());
			pstmt.setString(3, workingDay.getBreakTimeStart());
			pstmt.setString(4, workingDay.getBreakTimeEnd());
			pstmt.setString(5, workingDay.getNapTime());
			pstmt.setInt(6, workingDay.getStatusCode());
			pstmt.setDate(7, sqlDate);

			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	//日付とIDから情報取得
	public static WorkingDay selectByDayAndEmployeeId(String selectDay,Integer employeeId) {

		String sql = "select * from " + tableName + " where date = '" + selectDay +"' AND employee_id = " +employeeId;

		try(ResultSet rs = DBCommon.getResultSet(sql);){

			WorkingDay workingDay = new WorkingDay();
			while(rs.next()){
				workingDay.setId(rs.getInt("id"));
				workingDay.setDate(rs.getDate("date"));
				workingDay.setWeek(rs.getInt("week"));
				workingDay.setAttendanceTime(rs.getString("attendance_time"));
				workingDay.setLeaveTime(rs.getString("leave_time"));
				workingDay.setBreakTimeStart(rs.getString("break_time_start"));
				workingDay.setBreakTimeEnd(rs.getString("break_time_end"));
				workingDay.setNapTime(rs.getString("nap_time"));
				workingDay.setEmployeeId(rs.getInt("employee_id"));
				workingDay.setLegalFlag(rs.getInt("legal_flag"));
				workingDay.setStatusCode(rs.getInt("attendance_status"));
			}
			return workingDay;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
	}

	//年月、従業員IDから情報取得
	public static WorkingDay selectAllByEmployeeId(Integer year, Integer month,Integer day, Integer userId) {
		String sql = "SELECT DATE_PART('YEAR', date) AS year,DATE_PART('MONTH', date) AS month,DATE_PART('DAY', date) AS day,work.id,week,attendance_time,leave_time,break_time_start,break_time_end,nap_time,legal_flag,attendance_status,status_name FROM " + tableName + " work INNER JOIN attendance_status attend ON work.attendance_status = attend.id  WHERE employee_id = " + userId;


		try(ResultSet rs = DBCommon.getResultSet(sql);){

		WorkingDay workingDay = new WorkingDay();
		while(rs.next()){
			if(year == rs.getInt("year") && month == rs.getInt("month") && day == rs.getInt("day")){
				AttendanceStatus attend = new AttendanceStatus();

				workingDay.setId(rs.getInt("id"));
				workingDay.setWeek(rs.getInt("week"));
				workingDay.setAttendanceTime(rs.getString("attendance_time"));
				workingDay.setLeaveTime(rs.getString("leave_time"));
				workingDay.setBreakTimeStart(rs.getString("break_time_start"));
				workingDay.setBreakTimeEnd(rs.getString("break_time_end"));
				workingDay.setNapTime(rs.getString("nap_time"));
				workingDay.setLegalFlag(rs.getInt("legal_flag"));
				workingDay.setStatusCode(rs.getInt("attendance_status"));

				attend.setStatusName(rs.getString("status_name"));

				workingDay.setAttendanceStatus(attend);

			}
		}
		return workingDay;

		}catch(SQLException e){
			WorkingDay workingDay = new WorkingDay();
			return workingDay;
		}
	}




}
