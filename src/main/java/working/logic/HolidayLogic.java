package main.java.working.logic;

import java.util.ArrayList;
import java.util.List;

public class HolidayLogic {

	public static String getHolidayTime(List<String> dayStatus,List<String> workTimeList) {

		//時分格納用リスト
		List<Integer> HourList = new ArrayList<>();
		List<Integer> MinuteList =new ArrayList<>();

		for (int i = 0; i < workTimeList.size(); i++) {
			if (dayStatus.get(i).equals("法定")){
				String[] temptimeList = workTimeList.get(i).split(":");
				HourList.add(Integer.parseInt(temptimeList[0]));
				MinuteList.add(Integer.parseInt(temptimeList[1]));
			}

		}

		//休日出勤時間算出
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
