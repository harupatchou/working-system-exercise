package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import main.java.kot.common.WorkingDay;
import resources.DBManager;

public class WorkingDayDao {

	private static String tableName = "working_day";

	/*従業員情報のインサート*/
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


}