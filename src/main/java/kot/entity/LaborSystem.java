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

	/*労働制IDの定数*/
	//通常労働制
	public static final Integer NORMAL_LABOR_SYSTEM = 1;
	//変形労働制
	public static final Integer DEFORMATION_LABOR_SYSTEM = 2;
	//フレックス制
	public static final Integer FLEX_LABOR_SYSTEM = 3;

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
