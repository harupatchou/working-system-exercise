package main.java.working.common;

import main.java.working.entity.Employee;
import main.java.working.entity.Workingtype;


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
	private String statutoryOverWorkingTimeTotal;
	/*深夜労働時間*/
	private String nightTimeTotal;
	/*深夜残業時間*/
	private String overNightTimeTotal;
	/*休日出勤時間*/
	private String holidayTimeTotal;
	/*従業員*/
	private Employee employee;
	/*従業員種別*/
	private Workingtype workingtype;

	public CalculationWorkingTimeTotal(){
	}
	public CalculationWorkingTimeTotal(String workingTimeTotal, String legalOverWorkingTimeTotal,String statutoryOverWorkingTimeTotal,String nightTimeTotal,String overNightTimeTotal,Employee employee,Workingtype workingtype){
		this.workingTimeTotal = workingTimeTotal;
		this.legalOverWorkingTimeTotal = legalOverWorkingTimeTotal;
		this.statutoryOverWorkingTimeTotal = statutoryOverWorkingTimeTotal;
		this.nightTimeTotal = nightTimeTotal;
		this.overNightTimeTotal = overNightTimeTotal;
		this.employee = employee;
		this.workingtype = workingtype;
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

	public String getStatutoryOverWorkingTimeTotal() {
		return statutoryOverWorkingTimeTotal;
	}

	public void setStatutoryOverWorkingTimeTotal(String statutoryOverWorkingTimeTotal) {
		this.statutoryOverWorkingTimeTotal = statutoryOverWorkingTimeTotal;
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
	public String getHolidayTimeTotal() {
		return holidayTimeTotal;
	}
	public void setHolidayTimeTotal(String holidayTimeTotal) {
		this.holidayTimeTotal = holidayTimeTotal;
	}
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Workingtype getWorkingtype() {
		return workingtype;
	}
	public void setWorkingtype(Workingtype workingtype) {
		this.workingtype = workingtype;
	}
}