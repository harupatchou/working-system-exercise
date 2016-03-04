package main.java.jp.kot.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.jp.kot.common.Company;
import resources.DBManager;

public class CompanyDao {

	private static String tableName = "company";


	/*会社情報のインサート*/
	public static boolean registCompany(Company company){



		String sql = "INSERT INTO " + tableName + " (id, company_name, workingtype_id) VALUES (?, ?, ?)";

		try {
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, company.getId());
			pstmt.setString(2, company.getCompanyName());
			pstmt.setInt(3, company.getWorkingtypeId());


			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	public static Company getCompany(Integer companyId){

		String sql = "SELECT * FROM  " + tableName + " WHERE id = " + companyId;

		try(Connection con = DBManager.createConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();){

				Company company = new Company();
				while(rs.next()){
					company.setId(rs.getInt("id"));
					company.setCompanyName(rs.getString("company_name"));
					company.setWorkingtypeId(rs.getInt("workingtype_id"));
				}
				return company;

			}catch(SQLException e){
				System.err.println("SQL = " + sql);
				throw new RuntimeException("処理に失敗しました", e);
			}
	}

}
