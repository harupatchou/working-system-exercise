package main.java.kot.entity;
/**
 * 従業員種別情報
 * @author kuro
 **/
public class AttendanceTime {

	//ID
	private Integer id;
	//出勤時間
	private String start_time;
	//退勤時間
	private String end_time;
	//コアタイム開始時間
	private String core_time_strat;
	//コアタイム終了時間
	private String core_time_end;
	//従業員種別コード
	private Integer workingtype_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getCore_time_strat() {
		return core_time_strat;
	}

	public void setCore_time_strat(String core_time_strat) {
		this.core_time_strat = core_time_strat;
	}

	public String getCore_time_end() {
		return core_time_end;
	}

	public void setCore_time_end(String core_time_end) {
		this.core_time_end = core_time_end;
	}

	public Integer getWorkingtype_id() {
		return workingtype_id;
	}

	public void setWorkingtype_id(Integer workingtype_id) {
		this.workingtype_id = workingtype_id;
	}

	public AttendanceTime(){
	}

}
