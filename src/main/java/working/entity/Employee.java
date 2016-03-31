package main.java.working.entity;
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
	//company情報
	private Company company;
	//従業員種別
	private Workingtype workingType;

	public Employee(Integer employeeId, String firstName, String lastName,String password){
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
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
