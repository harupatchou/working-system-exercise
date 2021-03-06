package main.java.working.admin.calculation.logic;

import java.util.ArrayList;
import java.util.List;

import main.java.working.common.CalculationWorkingTimeTotal;
import main.java.working.dao.WorkingAllDao;
import main.java.working.entity.Company;
import main.java.working.entity.Employee;
import main.java.working.logic.DataLogic;

public class CalculationWorkingTimeLogic {

	//従業員ID・年月から総労働時間を取得
	public static CalculationWorkingTimeTotal workingTimeTotal(Integer employeeId, Integer month,Integer year){
		return WorkingAllDao.workingTimeTotal(employeeId, month,year);
	}

	//所属する従業員の月の総労働時間を取得
	public static List<CalculationWorkingTimeTotal> getCompanyCalculationWorkingTimeTotal(Company company,Integer year, Integer month){
		List<CalculationWorkingTimeTotal> calculationWorkingTimeTotalList = new ArrayList<>();
		List<Employee> employeeList = company.getEmployeeList();
		for(int i = 0; i < employeeList.size(); i++){
			CalculationWorkingTimeTotal total = WorkingAllDao.workingTimeTotal(employeeList.get(i).getEmployeeId(), month, year);
			total.setEmployee(employeeList.get(i));
			total.setWorkingtype(DataLogic.getWorkingtypeFromEmployeeId(employeeList.get(i).getEmployeeId()));
			calculationWorkingTimeTotalList.add(total);
		}
		return calculationWorkingTimeTotalList;
	}

	/*総労働時間算出*/
	public static String getWorkTimeTotal(List<String> timeList){

		//時分格納用リスト
		List<Integer> HourList = new ArrayList<>();
		List<Integer> MinuteList =new ArrayList<>();

		for (int i = 0; i < timeList.size(); i++) {
			String[] temptimeList = timeList.get(i).split(":");
			HourList.add(Integer.parseInt(temptimeList[0]));
			MinuteList.add(Integer.parseInt(temptimeList[1]));
		}

		//総労働時間算出
		int HourTotal = 0;
		for(int i = 0; i < HourList.size(); i++){
			HourTotal += HourList.get(i);
		}
		int MinuteTotal = 0;
		for (int i = 0; i < MinuteList.size(); i++) {
			MinuteTotal += MinuteList.get(i);
		}
		HourTotal += MinuteTotal / 60;
		MinuteTotal = MinuteTotal % 60;

		//時分をString型に変更
		String tempHourTotal = String.valueOf(HourTotal);
		String tempMinuteTotal;
		//分が一桁の場合は先頭に0を付与
		if(MinuteTotal < 10){
			tempMinuteTotal = "0" + String.valueOf(MinuteTotal);
		}else{
			tempMinuteTotal = String.valueOf(MinuteTotal);
		}

		String timeTotal = tempHourTotal + ":" + tempMinuteTotal;

		return timeTotal;
	}

}
