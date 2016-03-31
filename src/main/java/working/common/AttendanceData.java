package main.java.working.common;

import main.java.working.entity.Employee;
import main.java.working.entity.Overtime;
import main.java.working.entity.WorkingAll;
import main.java.working.entity.WorkingDay;
import main.java.working.entity.Workingtype;

/**
 * Attendance時に必要な情報
 * @author kuro
 **/
public class AttendanceData {

	Employee employee;
	Workingtype workingtype;
	WorkingDay workingDay;
	WorkingAll workingAll;
	Overtime overtime;
	InsertDay insertDay;
	Schedule schedule;
	StrTime strTime;

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
	public WorkingDay getWorkingDay() {
		return workingDay;
	}
	public void setWorkingDay(WorkingDay workingDay) {
		this.workingDay = workingDay;
	}
	public WorkingAll getWorkingAll() {
		return workingAll;
	}
	public void setWorkingAll(WorkingAll workingAll) {
		this.workingAll = workingAll;
	}
	public Overtime getOvertime() {
		return overtime;
	}
	public void setOvertime(Overtime overtime) {
		this.overtime = overtime;
	}
	public InsertDay getInsertDay() {
		return insertDay;
	}
	public void setInsertDay(InsertDay insertDay) {
		this.insertDay = insertDay;
	}
	public Schedule getSchedule() {
		return schedule;
	}
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	public StrTime getStrTime() {
		return strTime;
	}
	public void setStrTime(StrTime strTime) {
		this.strTime = strTime;
	}

}
