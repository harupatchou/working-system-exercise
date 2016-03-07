package main.java.kot.common;
/**
 * 従業員情報
 * @author ueno
 **/
public class Employee {

	//従業員ID
	private Integer employeeId;
	//姓
	private String firstName;
	//名
	private String lastName;
	//パスワード
	private String password;
	//会社ID
	private Integer companyId;

	public Employee(){
	}

	public Employee(Integer employeeId, String firstName, String lastName,String password,Integer companyId){
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.companyId = companyId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}


}
