package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.kot.common.database.DBManager;
import main.java.kot.entity.AttendanceStatus;

public class AttendanceStatusDao {

	private static String tableName = "attendance_status";

	//情報全て取得
	public static List<AttendanceStatus> selectAttendStatusAll() {
		String sql = "select * from " + tableName;

		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){

			List<AttendanceStatus> statusList = new ArrayList<>();
			while(rs.next()){
				AttendanceStatus attendanceStatus = new AttendanceStatus();

				attendanceStatus.setId(rs.getInt("id"));
				attendanceStatus.setStatusName(rs.getString("status_name"));

				statusList.add(attendanceStatus);
			}
			return statusList;
		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
	}

}
