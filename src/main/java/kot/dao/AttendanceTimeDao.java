package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.kot.common.database.DBManager;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.LaborSystem;

public class AttendanceTimeDao {

	private static String tableName = "attendance_time";

	/*従業員種別IDから種別ごとの労働時間を取得*/
	public static AttendanceTime getAttendanceTimeFromWrokingtypeId(Integer labor_system_id){
		String sql = "SELECT * FROM " + tableName + " WHERE labor_system_id = " + labor_system_id;
		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){

			AttendanceTime attendanceTime = new AttendanceTime();
			while(rs.next()){
				attendanceTime.setId(rs.getInt("id"));
				attendanceTime.setStartTime(rs.getString("start_time"));
				attendanceTime.setEndTime(rs.getString("end_time"));
				attendanceTime.setCoreTimeStrat(rs.getString("core_time_start"));
				attendanceTime.setCoreTimeEnd(rs.getString("core_time_end"));
				attendanceTime.setLaborSystemId(rs.getInt("labor_system_id"));
			}

			return attendanceTime;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}

	}

	/*カンパニーIDから労働時間を取得*/
	public static List<AttendanceTime> getAttendanceTime(Integer id) {
		String sql = "SELECT at.*,ls.* FROM attendance_time at INNER JOIN labor_system ls ON at.labor_system_id = ls.id WHERE at.company_id = " + id;
		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){

			List<AttendanceTime> attendanceTimeList = new ArrayList<>();
			while(rs.next()){
				AttendanceTime attendanceTime = new AttendanceTime();

				attendanceTime.setId(rs.getInt("id"));
				attendanceTime.setStartTime(rs.getString("start_time"));
				attendanceTime.setEndTime(rs.getString("end_time"));
				attendanceTime.setCoreTimeStrat(rs.getString("core_time_start"));
				attendanceTime.setCoreTimeEnd(rs.getString("core_time_end"));
				attendanceTime.setLaborSystemId(rs.getInt("labor_system_id"));

				LaborSystem tempLaborSystem = new LaborSystem();
				tempLaborSystem.setId(rs.getInt("labor_system_id"));
				tempLaborSystem.setLaborSystemName(rs.getString("labor_system_name"));

				attendanceTime.setLaborSystem(tempLaborSystem);

				attendanceTimeList.add(attendanceTime);
			}

			return attendanceTimeList;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
	}

}
