package main.java.kot.entity;

import java.util.List;

/**
 * 会社情報
 * @author kuro
 **/
public class Company {

	//会社ID
	private Integer id;
	//社名
	private String companyName;
	//masterID
	private Integer masterId;
	//従業員リスト
	private List<Employee> employeeList;
	//従業員種別リスト
	private List<Workingtype> workingtypeList;

	public Company(Integer id, String companyName, Integer workingtypeId,Integer masterId,List<Employee> employeeList,List<Workingtype> workingtypeList){
		this.id = id;
		this.companyName = companyName;
		this.masterId = masterId;
		this.employeeList = employeeList;
		this.workingtypeList = workingtypeList;
	}

	public Company() {
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public Integer getMasterId() {
		return masterId;
	}
	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}

	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public List<Workingtype> getWorkingtypeList() {
		return workingtypeList;
	}

	public void setWorkingtypeList(List<Workingtype> workingtypeList) {
		this.workingtypeList = workingtypeList;
	}

}
