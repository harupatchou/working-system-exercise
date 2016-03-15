package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	/*残業情報のアップデート*/
	public static boolean updateOvertime(Overtime overtime) {
		String sql = "UPDATE " + tableName + " SET legal_overtime = ?,statutory_overtime = ? ,night_overtime = ?,statutory_night_overtime = ?,daily_id = ? WHERE id = " + overtime.getOvertimeId();

		try {
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);

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

	/*デイリーIDから残業情報の有無を判定*/
	public static boolean getOvertimeFromDailyId(Overtime overtime){
		String sql = "SELECT * FROM " + tableName + " WHERE daily_id = " + overtime.getDailyId();

		try(Connection con = DBManager.createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();){

				boolean overtimeJudge = false;

				while(rs.next()){
					Overtime tempOvertime = new Overtime();
					tempOvertime.setOvertimeId(rs.getInt("id"));
					tempOvertime.setLegalOvertime(rs.getString("legal_overtime"));
					tempOvertime.setStatutoryOvertime(rs.getString("statutory_overtime"));
					tempOvertime.setNightOvertime(rs.getString("night_overtime"));
					tempOvertime.setStatutoryNightOvertime(rs.getString("statutory_night_overtime"));
					tempOvertime.setDailyId(rs.getInt("daily_id"));
					overtimeJudge = true;
				}

				return overtimeJudge;

			}catch(SQLException e){
				System.err.println("SQL = " + sql);
				throw new RuntimeException("処理に失敗しました", e);
			}
	}


}
