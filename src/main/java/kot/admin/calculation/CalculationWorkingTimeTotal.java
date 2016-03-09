package main.java.kot.admin.calculation;


/**
 * 総労働・残業時間
 * @author ueno
 **/
public class CalculationWorkingTimeTotal {

	/*月情報*/
	private Integer month;
	/*年情報*/
	private Integer year;
	/*総労働時間*/
	private String workingTimeTotal;
	/*総法定内残業時間*/
	private String legalOverWorkingTimeTotal;
	/*総法定外残業時間*/
	private String nonLegalOverWorkingTimeTotal;
	/*深夜労働時間*/
	private String nightTimeTotal;
	/*深夜残業時間*/
	private String overNightTimeTotal;


	public CalculationWorkingTimeTotal(){
	}

	public CalculationWorkingTimeTotal(String workingTimeTotal, String legalOverWorkingTimeTotal,String nonLegalOverWorkingTimeTotal,String nightTimeTotal,String overNightTimeTotal ){
		this.workingTimeTotal = workingTimeTotal;
		this.legalOverWorkingTimeTotal = legalOverWorkingTimeTotal;
		this.nonLegalOverWorkingTimeTotal = nonLegalOverWorkingTimeTotal;
		this.nightTimeTotal = nightTimeTotal;
		this.overNightTimeTotal = overNightTimeTotal;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getWorkingTimeTotal() {
		return workingTimeTotal;
	}
	public void setWorkingTimeTotal(String workingTimeAll) {
		this.workingTimeTotal = workingTimeAll;
	}
	public String getLegalOverWorkingTimeTotal() {
		return legalOverWorkingTimeTotal;
	}

	public void setLegalOverWorkingTimeTotal(String legalOverWorkingTimeTotal) {
		this.legalOverWorkingTimeTotal = legalOverWorkingTimeTotal;
	}

	public String getNonLegalOverWorkingTimeTotal() {
		return nonLegalOverWorkingTimeTotal;
	}

	public void setNonLegalOverWorkingTimeTotal(String nonLegalOverWorkingTimeTotal) {
		this.nonLegalOverWorkingTimeTotal = nonLegalOverWorkingTimeTotal;
	}

	public String getNightTimeTotal() {
		return nightTimeTotal;
	}
	public void setNightTimeTotal(String nightTimeTotal) {
		this.nightTimeTotal = nightTimeTotal;
	}
	public String getOverNightTimeTotal() {
		return overNightTimeTotal;
	}
	public void setOverNightTimeTotal(String overNightTimeTotal) {
		this.overNightTimeTotal = overNightTimeTotal;
	}
}