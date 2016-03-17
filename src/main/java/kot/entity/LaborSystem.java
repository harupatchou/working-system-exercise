package main.java.kot.entity;
/**
 * 従業員種別情報
 * @author kuro
 **/
public class LaborSystem {

	//ID
	private Integer id;
	//種別名
	private String laborSystemName;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLaborSystemName() {
		return laborSystemName;
	}
	public void setLaborSystemName(String laborName) {
		this.laborSystemName = laborName;
	}
}
