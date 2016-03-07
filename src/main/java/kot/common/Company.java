package main.java.kot.common;
/**
 * 会社情報
 * @author kuro
 **/
public class Company {

	//会社ID
	private Integer id;
	//社名
	private String companyName;
	//従業員種別ID
	private Integer workingtypeId;

	public Company(Integer id, String companyName, Integer workingtypeId){
		this.id = id;
		this.companyName = companyName;
		this.workingtypeId = workingtypeId;
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
	public Integer getWorkingtypeId() {
		return workingtypeId;
	}
	public void setWorkingtypeId(Integer workingtypeId) {
		this.workingtypeId = workingtypeId;
	}



}
