package main.java.jp.kot.common;
/**
 * 従業員種別情報
 * @author kuro
 **/
public class Workingtype {

	//ID
	private Integer id;
	//種別名
	private String workingName;
	//種別毎時間（フレックスの場合はfが入る）
	private String workingTime;

	public Workingtype(){
	}

	public Workingtype(Integer id, String workingName, String workingTime){
		this.id = id;
		this.workingName = workingName;
		this.workingTime = workingTime;
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
	public String getWorkingTime() {
		return workingTime;
	}
	public void setWorkingTime(String workingTime) {
		this.workingTime = workingTime;
	}



}
