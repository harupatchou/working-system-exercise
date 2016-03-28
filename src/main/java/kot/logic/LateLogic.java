package main.java.kot.logic;

public class LateLogic {

	//通常、変形労働時遅刻判別
	public static String lateCheckForNormal(String provisionAttendTime,String startTime) {

		String[] tempProvision = provisionAttendTime.split(":");
		String[] tempAttendance = startTime.split(":");

		//TODO 一旦Stringにする意味は？
		String strProvisionHour = tempProvision[0];
		String strProvisionMinutes = tempProvision[1];
		String strAttendanceHour = tempAttendance[0];
		String strAttendanceMinutes = tempAttendance[1];

		Integer provisionHour = Integer.parseInt(strProvisionHour);
		Integer provisionMinutes = Integer.parseInt(strProvisionMinutes);
		Integer attendanceHour = Integer.parseInt(strAttendanceHour);
		Integer attendanceMinutes = Integer.parseInt(strAttendanceMinutes);

		String tempLateHour = "";
		String tempLateMinutes = "";

		String lateTime = "";

		//10:00
		//09:30

		//出社時間が規定時間を超えていれば
		if(provisionHour<=attendanceHour){
			//出社時間から規定時間を引く
			tempLateHour = String.valueOf(attendanceHour - provisionHour);//FIXME String.valueOfを使いまくる意味ある？
			//分数計算
			if(provisionMinutes<=attendanceMinutes){
				tempLateMinutes = String.valueOf(attendanceMinutes - provisionMinutes);
			}else{
				tempLateHour = String.valueOf(attendanceHour - provisionHour - 1);
				tempLateMinutes = String.valueOf(60 - (provisionMinutes - attendanceMinutes));
			}

			if(tempLateMinutes.length()==1){
				tempLateMinutes = "0" + tempLateMinutes;
			}

			lateTime += tempLateHour + ":" + tempLateMinutes; //FIXME +=の意味がわかんない

		}else{
			lateTime="0:00";
		}

		return lateTime;
	}



}
