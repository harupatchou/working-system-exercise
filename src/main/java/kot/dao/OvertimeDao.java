package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import main.java.kot.common.database.DBManager;
import main.java.kot.entity.Overtime;


public class OvertimeDao {

	private static String tableName = "overtime";

	/*残業情報のインサート*/
	public static boolean insertOvertime(Overtime overtime) {
		String sql = "INSERT INTO " + tableName + " (legal_overtime,statutory_overtime,night_overtime,statutory_night_overtime,daily_id) VALUES (?, ?, ?, ?, ?)";

		try {
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);

			//TODO 微妙
			pstmt.setString(1,overtime.getLegalOvertime());
			pstmt.setString(2,overtime.getStatutoryOvertime());
			pstmt.setString(3, overtime.getNightOvertime());
			pstmt.setString(4, overtime.getStatutoryNightOvertime());
			pstmt.setInt(5, overtime.getDailyId());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}


}
