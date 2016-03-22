package main.java.kot.logic;

import java.util.Calendar;
import java.util.Date;

import main.java.kot.common.TempTime;
import main.java.kot.common.workingtime.constant.UpperLimitTime;

/**
 * 労働上限時間ロジック
 * @author ueno
 **/
public class UpperLimitTimeLogic{

	/*月の労働上限時間を取得*/
	public static String getUpperLimitTime(Date date){

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;
		double tempUpperLimitTime = 0;
		if(month == 12){
			tempUpperLimitTime = UpperLimitTime.DecemberLimit;
		}else if(month == 1){
			tempUpperLimitTime = UpperLimitTime.JanuaryLimit;
		}else if(month == 2){
			tempUpperLimitTime = UpperLimitTime.February;
		}else if(month == 3){
			tempUpperLimitTime = UpperLimitTime.MarchLimit;
		}
		String upperLimitTime = WorkingTimeLogic.getTimeDoubleToString(tempUpperLimitTime);
		return upperLimitTime;
	}

	/* 労働上限時間判定 (労働時間に応じて警告メッセージを返す) */
	public static String decisionLimitTime(String currentWorkingTimeTotal, String monthlyUpperLimitTime ){

		String alertMessage = "";

		String largeTime = WorkingTimeLogic.compareWorkingTime(currentWorkingTimeTotal, monthlyUpperLimitTime);
		if(largeTime.equals(currentWorkingTimeTotal)){
			alertMessage = "当月の労働時間が上限に達しました";
		}else{
			//上限時間まで20時間を切ったら警告
			TempTime subtractionWorkingTime = WorkingTimeLogic.subtractionWorkingTimeTempTime(monthlyUpperLimitTime,currentWorkingTimeTotal);

			if(subtractionWorkingTime.getHour() < 20){
				alertMessage = "残り" + subtractionWorkingTime.getHour() + "時間" + subtractionWorkingTime.getMinute() + "分で当月の労働上限時間に達します";
			}
		}

		return alertMessage;
	}

}
