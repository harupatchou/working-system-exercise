package main.java.kot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.kot.common.database.DBCommon;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.Company;
import main.java.kot.entity.CoreTime;
import main.java.kot.entity.LaborSystem;
import main.java.kot.entity.Workingtype;

public class WorkingtypeDao {

	private static String tableName = "working_type";


	/*従業員種別情報のインサート*/
	public static boolean registWorkingtype(Workingtype workingtype){

		String sql = "INSERT INTO " + tableName + " (id, working_name, labor_system_id ,company_id) VALUES (?, ?,?, ?)";

		try {
			PreparedStatement pstmt = DBCommon.getPreparedStatement(sql);

			pstmt.setInt(1, workingtype.getId());
			pstmt.setString(2, workingtype.getWorkingName());
			pstmt.setInt(3, workingtype.getLaborSystem().getId());
			pstmt.setInt(4, workingtype.getCompany().getId());


			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	/*従業員種別IDから従業員種別情報を取得*/
	public static Workingtype getWorkingtype(Integer workingtypeId){
		String sql = "SELECT work.*,labor.*,attend.* FROM " + tableName + " work JOIN labor_system labor ON work.labor_system_id = labor.id "
				+ "LEFT OUTER JOIN attendance_time attend ON labor.id = attend.labor_system_id WHERE work.id = " + workingtypeId;

		try(ResultSet rs = DBCommon.getResultSet(sql);){

			Workingtype workingtype = new Workingtype();
				while(rs.next()){
					workingtype.setId(rs.getInt("id"));
					workingtype.setWorkingName(rs.getString("working_name"));

					Company tempCompany = new Company();
					tempCompany.setId(rs.getInt("company_id"));
					workingtype.setCompany(tempCompany);

					LaborSystem tempLabor = new LaborSystem();
					tempLabor.setId(rs.getInt("labor_system_id"));
					tempLabor.setLaborSystemName(rs.getString("labor_system_name"));
					workingtype.setLaborSystem(tempLabor);

					AttendanceTime tempAttend = new AttendanceTime();
					tempAttend.setStartTime(rs.getString("start_time"));
					tempAttend.setEndTime(rs.getString("end_time"));

					CoreTime tempCore = new CoreTime();
					tempCore.setCoreTimeStrat(rs.getString("core_time_start"));
					tempCore.setCoreTimeEnd(rs.getString("core_time_end"));
					tempAttend.setCoreTime(tempCore);
					workingtype.setAttendanceTime(tempAttend);
				}
				return workingtype;

			}catch(SQLException e){
				System.err.println("SQL = " + sql);
				throw new RuntimeException("処理に失敗しました", e);
			}
	}

	/*会社IDから従業員種別情報を取得*/
	public static List<Workingtype>  getWorkingtypeFromCompanyId(Integer companyId){

		String sql = "SELECT work.*,labor.*,attend.* FROM " + tableName + " work JOIN labor_system labor ON work.labor_system_id = labor.id "
				+ "LEFT OUTER JOIN attendance_time attend ON labor.id = attend.labor_system_id WHERE work.id = " + companyId;

		try(ResultSet rs = DBCommon.getResultSet(sql);){

			List<Workingtype> workingytpeList = new ArrayList<>();
				while(rs.next()){
					Workingtype workingtype = new Workingtype();
					workingtype.setId(rs.getInt("id"));
					workingtype.setWorkingName(rs.getString("working_name"));

					Company tempCompany = new Company();
					tempCompany.setId(rs.getInt("company_id"));
					workingtype.setCompany(tempCompany);

					LaborSystem tempLabor = new LaborSystem();
					tempLabor.setId(rs.getInt("labor_system_id"));
					tempLabor.setLaborSystemName(rs.getString("labor_system_name"));
					workingtype.setLaborSystem(tempLabor);

					AttendanceTime tempAttend = new AttendanceTime();
					tempAttend.setStartTime(rs.getString("start_time"));
					tempAttend.setEndTime(rs.getString("end_time"));

					CoreTime tempCore = new CoreTime();
					tempCore.setCoreTimeStrat(rs.getString("core_time_start"));
					tempCore.setCoreTimeEnd(rs.getString("core_time_end"));
					tempAttend.setCoreTime(tempCore);
					workingtype.setAttendanceTime(tempAttend);

					workingytpeList.add(workingtype);
				}
				return workingytpeList;

			}catch(SQLException e){
				System.err.println("SQL = " + sql);
				throw new RuntimeException("処理に失敗しました", e);
			}
	}


}
