package main.java.kot.logic;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import main.java.kot.common.Overtime;

public class NonLegalWorkingTimeLogic {


	public static Overtime getOvertime(){

		//決め打ち
		String start = "9:00";
		String end = "18:45";

		String[] startArray = start.split(":");
		String[] endArray = end.split(":");

		int startTimeHour = Integer.parseInt(startArray[0]);
		int startTimeMinute = Integer.parseInt(startArray[1]);
		int endTimeHour = Integer.parseInt(endArray[0]);
		int endTimeMinute = Integer.parseInt(endArray[1]);

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm" );

		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();

		startTime.set(Calendar.HOUR_OF_DAY, startTimeHour);
		startTime.set( Calendar.MINUTE, startTimeMinute);
		startTime.set( Calendar.SECOND, 00 );

		endTime.set( Calendar.HOUR_OF_DAY, endTimeHour);
		endTime.set( Calendar.MINUTE, endTimeMinute);
		endTime.set( Calendar.SECOND, 00 );



		return null;
	}
}
