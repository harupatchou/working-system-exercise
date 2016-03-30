package main.java.kot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.kot.common.database.DBCommon;
import main.java.kot.entity.Company;
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

					workingtype.getWorkingTypeByResultSet(rs);

				}
				return workingtype;

			}catch(SQLException e){
				System.err.println("SQL = " + sql);
				throw new RuntimeException("処理に失敗しました", e);
			}
	}

	/*会社IDから従業員種別情報を取得*/
	public static List<Workingtype>  getWorkingtypeFromCompanyId(Company company){

		String sql = "SELECT work.*,labor.*,attend.* FROM " + tableName + " work JOIN labor_system labor ON work.labor_system_id = labor.id "
				+ "LEFT OUTER JOIN attendance_time attend ON labor.id = attend.labor_system_id WHERE work.company_id = " + company.getId();

		try(ResultSet rs = DBCommon.getResultSet(sql);){

			List<Workingtype> workingytpeList = new ArrayList<>();
				while(rs.next()){

					Workingtype workingtype = new Workingtype();
					workingtype.getWorkingTypeByResultSet(rs);

					workingytpeList.add(workingtype);
				}
				return workingytpeList;

			}catch(SQLException e){
				System.err.println("SQL = " + sql);
				throw new RuntimeException("処理に失敗しました", e);
			}
	}


}
