package main.java.kot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.kot.common.database.DBcommon;
import main.java.kot.entity.LaborSystem;

public class LaborSystemDao {


	private static String tableName = "labor_system";

	//労働制IDから労働制情報取得
	public static LaborSystem getLaborSystem(Integer laborSystemId){

		String sql = "SELECT * FROM  " + tableName + " WHERE id = " + laborSystemId;

		try(ResultSet rs = DBcommon.getResultSet(sql);){

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
