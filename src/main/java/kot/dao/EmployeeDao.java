package main.java.kot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.kot.common.database.DBcommon;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.entity.Workingtype;

public class EmployeeDao {

	private static String tableName = "employee";


	/*従業員IDから従業員情報取得*/
	public static Employee getEmployee(Integer employeeId){
		String sql = "SELECT emp.*,com.*,work.* FROM " + tableName + " emp INNER JOIN company com ON emp.company_id = com.id "
				+ "INNER JOIN working_type work ON emp.working_type_id = work.id  WHERE emp.id = " + employeeId;
		try(ResultSet rs = DBcommon.getResultSet(sql);){

			Employee employee = new Employee();
			while(rs.next()){
				employee.setEmployeeId(rs.getInt("id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setPassword(rs.getString("password"));

				Company tempCom = new Company();
				tempCom.setId(rs.getInt("company_id"));
				tempCom.setCompanyName(rs.getString("company_name"));
				employee.setCompany(tempCom);

				Workingtype tempWork = new Workingtype();
				tempWork.setId(rs.getInt("working_type_id"));
				tempWork.setWorkingName(rs.getString("working_name"));
				employee.setWorkingType(tempWork);
			}
			return employee;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
	}

	/*従業員IDから取得したIDでCompanyとWorkingType取得*/
	public static Employee getEmployeeWithCompany(Integer employeeId){
		String sql = "SELECT emp.*,com.*,work.* FROM employee emp INNER JOIN company com ON emp.company_id = com.id "
				+ "INNER JOIN working_type work ON emp.working_type_id = work.id  WHERE emp.id = "+ employeeId;
		try(ResultSet rs = DBcommon.getResultSet(sql);){

			Employee employee = new Employee();
			while(rs.next()){
				employee.setEmployeeId(rs.getInt("id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setPassword(rs.getString("password"));

				Company tempCom = new Company();
				tempCom.setId(rs.getInt("company_id"));
				tempCom.setCompanyName(rs.getString("company_name"));
				employee.setCompany(tempCom);

				Workingtype tempWork = new Workingtype();
				tempWork.setId(rs.getInt("working_type_id"));
				tempWork.setWorkingName(rs.getString("working_name"));
				employee.setWorkingType(tempWork);

			}
			return employee;

		}catch(SQLException e){
			System.err.println("SQL = " + sql);
			throw new RuntimeException("処理に失敗しました", e);
		}
	}


	/*会社IDから従業員リスト取得*/
	public static Company getEmployeeFromCompanyId(Integer companyId){
		String sql = "SELECT emp.*, com.*, work.* FROM " + tableName + " emp JOIN company com ON emp.company_id = com.id "
				+ "JOIN working_type work ON emp.working_type_id = work.id WHERE emp.company_id =" + companyId;
		try(ResultSet rs = DBcommon.getResultSet(sql);){

			Company company = new Company();
			List<Employee> employeeList = new ArrayList<>();
			while(rs.next()){
				Employee employee = new Employee();
				employee.setEmployeeId(rs.getInt("id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setPassword(rs.getString("password"));

				Company tempCom = new Company();
				tempCom.setId(rs.getInt("company_id"));
				tempCom.setCompanyName(rs.getString("company_name"));
				employee.setCompany(tempCom);

				Workingtype tempWork = new Workingtype();
				tempWork.setId(rs.getInt("working_type_id"));
				tempWork.setWorkingName(rs.getString("working_name"));
				employee.setWorkingType(tempWork);

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

		String sql = "INSERT INTO " + tableName + " (id, first_name, last_name, password, company_id, working_type_id) VALUES (?, ?, ?, ?, ?, ?)";

		try {
            PreparedStatement pstmt = DBcommon.getPreparedStatement(sql);

			pstmt.setInt(1, employee.getEmployeeId());
			pstmt.setString(2, employee.getFirstName());
			pstmt.setString(3, employee.getLastName());
			pstmt.setString(4, employee.getPassword());
			pstmt.setInt(5, employee.getCompany().getId());
			pstmt.setInt(6, employee.getWorkingType().getId());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}

	/*従業員情報のアップデート*/
	public static boolean updateEmployee(Employee employee) {
		String sql = "UPDATE " + tableName + " SET first_name = ?, last_name = ?, password = ?, company_id = ?, working_type_id = ? WHERE id = " + employee.getEmployeeId();
		try {
			PreparedStatement pstmt = DBcommon.getPreparedStatement(sql);

			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getLastName());
			pstmt.setString(3, employee.getPassword());
			pstmt.setInt(4, employee.getCompany().getId());
			pstmt.setInt(5, employee.getWorkingType().getId());

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
		try(ResultSet rs = DBcommon.getResultSet(sql);){

			Employee employee = new Employee();
			Company company = new Company();
			while(rs.next()){
				employee.setEmployeeId(rs.getInt("id"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setPassword(rs.getString("password"));

				company.setId(rs.getInt("company_id"));
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
			PreparedStatement pstmt = DBcommon.getPreparedStatement(sql);

			pstmt.setString(1,employee.getPassword());

			pstmt.executeUpdate();

			return true;

		} catch (SQLException ex) {
			System.err.println(ex);
			throw new RuntimeException();
		}
	}
}
