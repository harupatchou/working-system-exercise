package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.kot.common.database.DBManager;
import main.java.kot.entity.Employee;

public class EmployeeDao {

	private static String tableName = "employee";

	/*従業員IDから従業員情報取得*/
	public static Employee getEmployee(Integer employeeId){
		String sql = "SELECT * FROM " + tableName + " WHERE id = " + employeeId;
		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){

			Employee employee = new Employee();
			while(rs.next()){
				employee.setEmployeeId(rs.getInt("id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setPassword(rs.getString("password"));
				employee.setCompanyId(rs.getInt("company_id"));
				employee.setWorkingTypeId(rs.getInt("workingtype_id"));
			}
			return employee;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
	}

	/*従業員情報のインサート*/
	public static boolean registEmployee(Employee employee){



		String sql = "INSERT INTO " + tableName + " (id, first_name, last_name, password, company_id) VALUES (?, ?, ?, ?, ?)";

		try {
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, employee.getEmployeeId());
			pstmt.setString(2, employee.getFirstName());
			pstmt.setString(3, employee.getLastName());
			pstmt.setString(4, employee.getPassword());
			pstmt.setInt(5, employee.getCompanyId());


			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}


}
