package main.java.kot.common.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//FIXME キャメルケースで書いて
public class DBcommon {

	/*ResultSetオブジェクト作成*/
	//static final メソッドってどういう意味？
	public static final ResultSet getResultSet(String sql){
		try{
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			return rs;
		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
	}

	/*PreparedStatementオブジェクト作成*/
	public static final PreparedStatement getPreparedStatement(String sql){
		try{
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
			return pstmt;
		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
	}

}
