package main.java.jp.kot.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.jp.kot.common.Workingtype;
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


}
