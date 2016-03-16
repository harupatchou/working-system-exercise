package main.java.kot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.kot.common.database.DBManager;
import main.java.kot.entity.Company;
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

	/*会社IDから従業員リスト取得*/
	public static Company getEmployeeFromCompanyId(Integer companyId){
		String sql = "SELECT * FROM " + tableName + " WHERE company_id = " + companyId;
		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){

			Company company = new Company();
			List<Employee> employeeList = new ArrayList<>();
			while(rs.next()){
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt("id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setPassword(rs.getString("password"));
				employee.setCompanyId(rs.getInt("company_id"));
				employee.setWorkingTypeId(rs.getInt("workingtype_id"));
				employeeList.add(employee);
			}
			company.setEmployeeList(employeeList);
			return company;

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

	//ログイン情報と比較用の取得
	public static Employee LoginCheckInfo(Integer employeeId) {
		String sql = "SELECT e.*,c.company_name,c.master_id FROM " + tableName + " e INNER JOIN company c ON e.company_id = c.id WHERE e.id = " + employeeId;
		try(Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();){

			Employee employee = new Employee();
			Company company = new Company();
			while(rs.next()){
				employee.setEmployeeId(rs.getInt("id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setPassword(rs.getString("password"));
				employee.setCompanyId(rs.getInt("company_id"));
				employee.setWorkingTypeId(rs.getInt("workingtype_id"));
				company.setCompanyName(rs.getString("company_name"));
				company.setMasterId(rs.getInt("master_id"));
				employee.setCompany(company);
			}
			return employee;

		}catch(SQLException e){
			Employee employee = new Employee();
			return employee;
		}
	}

	/*パスワードの変更*/
	public static boolean changePassword(Employee employee) {
		String sql = "UPDATE " + tableName + " SET password = ? WHERE id = " + employee.getEmployeeId();
		try {
			Connection con = DBManager.createConnection();
			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1,employee.getNewPassword());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}
}
