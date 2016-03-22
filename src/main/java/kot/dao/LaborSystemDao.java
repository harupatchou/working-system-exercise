package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.kot.common.database.DBManager;
import main.java.kot.entity.LaborSystem;

public class LaborSystemDao {


	private static String tableName = "labor_system";

	//労働制IDから労働制情報取得
	public static LaborSystem getLaborSystem(Integer laborSystemId){

		String sql = "SELECT * FROM  " + tableName + " WHERE id = " + laborSystemId;

		try(Connection con = DBManager.createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();){

			LaborSystem loborSystem = new LaborSystem();
				while(rs.next()){
					loborSystem.setId(rs.getInt("id"));
					loborSystem.setLaborSystemName(rs.getString("labor_system_name"));
				}
				return loborSystem;

			}catch(SQLException e){
				System.err.println("SQL = " + sql);
				throw new RuntimeException("処理に失敗しました", e);
			}
	}
}
