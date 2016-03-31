package main.java.working.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.working.common.database.DBCommon;
import main.java.working.entity.Company;

public class CompanyDao {

	private static String tableName = "company";


	/*会社情報のインサート*/
	public static boolean registCompany(Company company){
		String sql = "INSERT INTO " + tableName + " (id, company_name) VALUES (?,?)";

		try {
			PreparedStatement pstmt = DBCommon.getPreparedStatement(sql);

			pstmt.setInt(1, company.getId());
			pstmt.setString(2, company.getCompanyName());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	/*会社情報のアップデート*/
	public static boolean editCompany(Company company, Company userCompany){
		String sql = "UPDATE " + tableName + " SET id = ?,company_name = ? WHERE id = " + userCompany.getId();

		try {
			PreparedStatement pstmt = DBCommon.getPreparedStatement(sql);

			pstmt.setInt(1, company.getId());
			pstmt.setString(2, company.getCompanyName());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	//companyIdから情報取得
	public static Company getCompany(Integer companyId){
		String sql = "SELECT * FROM  " + tableName + " WHERE id = " + companyId;
		try(ResultSet rs = DBCommon.getResultSet(sql);){

				Company company = new Company();
				while(rs.next()){
					company.setId(rs.getInt("id"));
					company.setCompanyName(rs.getString("company_name"));
				}
				return company;

			}catch(SQLException e){
				System.err.println("SQL = " + sql);
				throw new RuntimeException("処理に失敗しました", e);
			}
	}

}
