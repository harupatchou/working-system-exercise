package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
			pstmt.setString(5,workingAll.getSatutoryOverTimeAll());
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

}
