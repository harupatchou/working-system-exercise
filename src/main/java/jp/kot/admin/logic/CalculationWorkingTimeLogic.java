package main.java.jp.kot.admin.logic;

import java.util.ArrayList;
import java.util.List;

import main.java.jp.kot.admin.dao.CompanyDao;
import main.java.jp.kot.admin.dao.EmployeeDao;
import main.java.jp.kot.admin.dao.WorkingTimeDao;
import main.java.jp.kot.admin.dao.WorkingtypeDao;
import main.java.jp.kot.common.Company;
import main.java.jp.kot.common.Employee;
import main.java.jp.kot.common.WorkingTimeTotal;
import main.java.jp.kot.common.Workingtype;


public class CalculationWorkingTimeLogic {

	//従業員IDから従業員情報を取得
	public static Employee getEmployee(Integer employeeId){
		return EmployeeDao.getEmployee(employeeId);
	}

	//従業員IDから総労働時間を取得
	public static WorkingTimeTotal workingTimeTotal(Integer employeeId){
		return WorkingTimeDao.workingTimeTotal(employeeId);
	}

	//会社IDから会社情報を取得
	public static Company getCompany(Integer companyId){
		return CompanyDao.getCompany(companyId);
	}

	//労働種別IDから労働種別情報を取得
	public static Workingtype getWorkingtype(Integer workingtypeId){
		return WorkingtypeDao.getWorkingtype(workingtypeId);
	}

	//従業員IDから労働種別情報を取得
	public static Workingtype getWorkingtypeFromEmployeeId(Integer employeeId){
		Integer companyId = getEmployee(employeeId).getCompanyId();
		Integer workingtypeId = getCompany(companyId).getWorkingtypeId();
		return getWorkingtype(workingtypeId);
	}


	//総労働時間算出
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
