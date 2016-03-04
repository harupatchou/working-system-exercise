package main.java.jp.kot.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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


}
