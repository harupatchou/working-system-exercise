package main.java.kot.entity;
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
	//新規パスワード(パスワード変更時用)
	private String newPassword;
	//company情報
	private Company company;
	//従業員種別
	private Workingtype workingType;

	public Employee(Integer employeeId, String firstName, String lastName,String password,String newPassword){
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.newPassword = newPassword;
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

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Employee(){
	}

	public Workingtype getWorkingType() {
		return workingType;
	}

	public void setWorkingType(Workingtype workingType) {
		this.workingType = workingType;
	}

}
