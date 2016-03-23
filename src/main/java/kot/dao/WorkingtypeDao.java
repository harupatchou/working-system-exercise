package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.kot.common.database.DBManager;
import main.java.kot.entity.LaborSystem;
import main.java.kot.entity.Workingtype;

public class WorkingtypeDao {

	private static String tableName = "working_type";


	/*従業員種別情報のインサート*/
	public static boolean registWorkingtype(Workingtype workingtype){



		String sql = "INSERT INTO " + tableName + " (id, working_name, labor_system_id ,company_id) VALUES (?, ?,?, ?)";

		try {
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, workingtype.getId());
			pstmt.setString(2, workingtype.getWorkingName());
			pstmt.setInt(3, workingtype.getLaborSystemId());
			pstmt.setInt(4, workingtype.getCompanyId());


			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	/*従業員種別IDから従業員種別情報を取得*/
	public static Workingtype getWorkingtype(Integer workingtypeId){
		String sql = "SELECT work.*,labor.* FROM working_type work JOIN labor_system labor ON work.labor_system_id = labor.id WHERE work.id = " + workingtypeId;

		try(Connection con = DBManager.createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();){

			Workingtype workingtype = new Workingtype();
				while(rs.next()){
					LaborSystem laborSystem = new LaborSystem();
					workingtype.setId(rs.getInt("id"));
					workingtype.setWorkingName(rs.getString("working_name"));
					workingtype.setLaborSystemId(rs.getInt("labor_system_id"));
					workingtype.setCompanyId(rs.getInt("company_id"));
					laborSystem.setId(workingtype.getLaborSystemId());
					laborSystem.setLaborSystemName(rs.getString("labor_system_name"));
					workingtype.setLaborSystem(laborSystem);
				}
				return workingtype;

			}catch(SQLException e){
				System.err.println("SQL = " + sql);
				throw new RuntimeException("処理に失敗しました", e);
			}
	}

	/*会社IDから従業員種別情報を取得*/
	public static List<Workingtype>  getWorkingtypeFromCompanyId(Integer companyId){

		String sql = "SELECT work.*,labor.* FROM working_type work JOIN labor_system labor ON work.labor_system_id = labor.id WHERE work.id = " + companyId;

		try(Connection con = DBManager.createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();){

			List<Workingtype> workingytpeList = new ArrayList<>();
				while(rs.next()){
					Workingtype workingtype = new Workingtype();
					LaborSystem laborSystem = new LaborSystem();
					workingtype.setId(rs.getInt("id"));
					workingtype.setWorkingName(rs.getString("working_name"));
					workingtype.setLaborSystemId(rs.getInt("labor_system_id"));
					workingtype.setCompanyId(rs.getInt("company_id"));
					laborSystem.setId(workingtype.getLaborSystemId());
					laborSystem.setLaborSystemName(rs.getString("labor_system_name"));
					workingtype.setLaborSystem(laborSystem);
					workingytpeList.add(workingtype);
				}
				return workingytpeList;

			}catch(SQLException e){
				System.err.println("SQL = " + sql);
				throw new RuntimeException("処理に失敗しました", e);
			}
	}


}
