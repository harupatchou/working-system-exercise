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
	//労働制
	private Integer labor_system_id;
	//会社コード
	private Integer company_id;
	//労働制
	private LaborSystem laborSystem;

	public Workingtype(){
	}

	public Workingtype(Integer id, String workingName, Integer labor_system_id,Integer company_id,LaborSystem laborSystem){
		this.id = id;
		this.workingName = workingName;
		this.labor_system_id = labor_system_id;
		this.company_id = company_id;
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
	public Integer getLaborSystemId() {
		return labor_system_id;
	}
	public void setLaborSystemId(Integer labor_system_id) {
		this.labor_system_id = labor_system_id;
	}
	public Integer getCompanyId() {
		return company_id;
	}
	public void setCompanyId(Integer company_id) {
		this.company_id = company_id;
	}
	public LaborSystem getLaborSystem() {
		return laborSystem;
	}
	public void setLaborSystem(LaborSystem loborSystem) {
		this.laborSystem = loborSystem;
	}

}
