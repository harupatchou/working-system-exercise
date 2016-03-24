package main.java.kot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.kot.common.database.DBcommon;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.Employee;
import main.java.kot.entity.LaborSystem;

public class AttendanceTimeDao {

	private static String tableName = "attendance_time";

	/*従業員種別IDから種別ごとの労働時間を取得*/
	public static AttendanceTime getAttendanceTimeFromLaborSystemId(Employee employee){
		Integer laborSystemId = employee.getWorkingType().getLaborSystem().getId();
		Integer companyId = employee.getCompany().getId();
		String sql = "SELECT * FROM " + tableName + " at JOIN labor_system labor ON at.labor_system_id = labor.id WHERE labor_system_id = " + laborSystemId + " AND company_id = " + companyId;
		try(ResultSet rs = DBcommon.getResultSet(sql);){

			AttendanceTime attendanceTime = new AttendanceTime();
			while(rs.next()){
				attendanceTime.setId(rs.getInt("id"));
				attendanceTime.setStartTime(rs.getString("start_time"));
				attendanceTime.setEndTime(rs.getString("end_time"));
				attendanceTime.setCoreTimeStrat(rs.getString("core_time_start"));
				attendanceTime.setCoreTimeEnd(rs.getString("core_time_end"));

				LaborSystem laborSystem = new LaborSystem();
				laborSystem.setId(rs.getInt("labor_system_id"));
				laborSystem.setLaborSystemName(rs.getString("labor_system_name"));
				attendanceTime.setLaborSystem(laborSystem);
			}

			return attendanceTime;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}

	}

	/*カンパニーIDから労働時間を取得*/
	public static List<AttendanceTime> getAttendanceTime(Integer id) {
		String sql = "SELECT at.*,ls.* FROM attendance_time at INNER JOIN labor_system ls ON at.labor_system_id = ls.id "
				+ "WHERE at.company_id = " + id + " ORDER BY at.labor_system_id";
		try(ResultSet rs = DBcommon.getResultSet(sql);){

			List<AttendanceTime> attendanceTimeList = new ArrayList<>();
			while(rs.next()){
				AttendanceTime attendanceTime = new AttendanceTime();

				attendanceTime.setId(rs.getInt("id"));
				attendanceTime.setStartTime(rs.getString("start_time"));
				attendanceTime.setEndTime(rs.getString("end_time"));
				attendanceTime.setCoreTimeStrat(rs.getString("core_time_start"));
				attendanceTime.setCoreTimeEnd(rs.getString("core_time_end"));

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

	//update
	public static void editAttendanceTime(AttendanceTime insertTime) {
		String sql = "UPDATE " + tableName + " SET start_time = ?,end_time = ? "
				+ "WHERE labor_system_id = " + insertTime.getLaborSystem().getId() +" AND company_id = " + insertTime.getCompany().getId();

		try {
			PreparedStatement pstmt = DBcommon.getPreparedStatement(sql);

			pstmt.setString(1, insertTime.getStartTime());
			pstmt.setString(2, insertTime.getEndTime());

			pstmt.executeUpdate();

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	public static Object selectAttendTime() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
