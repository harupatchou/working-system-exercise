package main.java.jp.kot.common;

public class Employee {
	//従業員ID
	private Integer employeeId;
	//従業員名
	private String employeeName;

	public Employee(){
	}

	public Employee(Integer employeeId, String employeeName){
	//	this.employeeId = employeeId;
		this.employeeName = employeeName;
	}
	/*
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}*/
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
}
