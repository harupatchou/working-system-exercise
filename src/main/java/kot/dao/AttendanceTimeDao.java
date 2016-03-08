package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.kot.common.AttendanceTime;
import resources.DBManager;

public class AttendanceTimeDao {

	private static String tableName = "attendance_time";

	/*従業員種別IDから種別ごとの労働時間を取得*/
	public static AttendanceTime getAttendanceTimeFromWrokingtypeId(Integer workingtypeId){
		String sql = "SELECT * FROM " + tableName + " WHERE workingtype_id = " + workingtypeId;
		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){

			AttendanceTime attendanceTime = new AttendanceTime();
			while(rs.next()){
				attendanceTime.setId(rs.getInt("id"));
				attendanceTime.setStart_time(rs.getString("start_time"));
				attendanceTime.setEnd_time(rs.getString("end_time"));
				attendanceTime.setCore_time_strat(rs.getString("core_time_start"));
				attendanceTime.setCore_time_end(rs.getString("core_time_end"));
				attendanceTime.setWorkingtype_id(rs.getInt("workingtype_id"));
			}

			return attendanceTime;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}

	}

}
