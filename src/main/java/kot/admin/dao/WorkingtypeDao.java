package main.java.kot.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.kot.common.Workingtype;
import resources.DBManager;

public class WorkingtypeDao {

	private static String tableName = "workingtype";


	/*従業員種別情報のインサート*/
	public static boolean registWorkingtype(Workingtype workingtype){



		String sql = "INSERT INTO " + tableName + " (id, working_name, working_time) VALUES (?, ?, ?)";

		try {
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, workingtype.getId());
			pstmt.setString(2, workingtype.getWorkingName());
			pstmt.setString(3, workingtype.getWorkingTime());


			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	public static Workingtype getWorkingtype(Integer workingtypeId){

		String sql = "SELECT * FROM  " + tableName + " WHERE id = " + workingtypeId;

		try(Connection con = DBManager.createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();){

			Workingtype workingtype = new Workingtype();
				while(rs.next()){
					workingtype.setId(rs.getInt("id"));
					workingtype.setWorkingName(rs.getString("working_name"));
					workingtype.setWorkingTime(rs.getString("working_time"));
				}
				return workingtype;

			}catch(SQLException e){
				System.err.println("SQL = " + sql);
				throw new RuntimeException("処理に失敗しました", e);
			}
	}


}
