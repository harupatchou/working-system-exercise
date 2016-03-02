package main.java.jp.kot.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import resources.DBManager;


public class WorkingTimeDao {

	private static String tableName = "-----";

	public static WorkingTimeAll WorkingTimeAll(String employeeName){
		String sql = "SELECT * FROM " + tableName;
		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);){

			//途中～
			WorkingTimeAll workingTimeAll = new WorkingTimeAll();

			try(ResultSet rs = pstmt.executeQuery()){
				WorkingTime workingTime = new WorkingTime();
				while(rs.next()){

				}
				return null;
			}

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}

	}
}
