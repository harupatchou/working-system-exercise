package main.java.jp.kot.admin.logic;

import java.util.ArrayList;
import java.util.List;

import main.java.jp.kot.admin.dao.WorkingTimeDao;
import main.java.jp.kot.common.WorkingTimeTotal;


public class CalculationWorkingTimeLogic {

	//従業員IDから総労働時間を取得
	public static WorkingTimeTotal workingTimeTotal(Integer employeeId){

		WorkingTimeTotal workingTimeAll = WorkingTimeDao.workingTimeTotal(employeeId);
		return workingTimeAll;
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
