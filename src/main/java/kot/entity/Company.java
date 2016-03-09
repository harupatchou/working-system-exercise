package main.java.kot.entity;
/**
 * 会社情報
 * @author kuro
 **/
public class Company {

	//会社ID
	private Integer id;
	//社名
	private String companyName;

	public Company(Integer id, String companyName, Integer workingtypeId){
		this.id = id;
		this.companyName = companyName;
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

}
