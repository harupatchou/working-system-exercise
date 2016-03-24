package main.java.kot.entity;
/**
 * 従業員種別情報
 * @author kuro
 **/
public class Workingtype {

	//ID
	private Integer id;
	//種別名
	private String workingName;
	//会社
	private Company company;
	//労働制
	private LaborSystem laborSystem;

	public Workingtype(){
	}

	public Workingtype(Integer id, String workingName, Integer company_id,Company company,LaborSystem laborSystem){
		this.id = id;
		this.workingName = workingName;
		this.company = company;
		this.laborSystem = laborSystem;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWorkingName() {
		return workingName;
	}
	public void setWorkingName(String workingName) {
		this.workingName = workingName;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public LaborSystem getLaborSystem() {
		return laborSystem;
	}
	public void setLaborSystem(LaborSystem loborSystem) {
		this.laborSystem = loborSystem;
	}

}
