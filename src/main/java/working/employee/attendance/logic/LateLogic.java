package main.java.working.employee.attendance.logic;

public class LateLogic {

	//通常、変形労働時遅刻判別
	public static String lateCheckForNormal(String provisionAttendTime,String startTime) {

		String[] tempProvision = provisionAttendTime.split(":");
		String[] tempAttendance = startTime.split(":");

		Integer provisionHour = Integer.parseInt(tempProvision[0]);
		Integer provisionMinutes = Integer.parseInt(tempProvision[1]);
		Integer attendanceHour = Integer.parseInt(tempAttendance[0]);
		Integer attendanceMinutes = Integer.parseInt(tempAttendance[1]);

		String tempLateHour = "";
		String tempLateMinutes = "";

		String lateTime = "";

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

			lateTime = tempLateHour + ":" + tempLateMinutes;

		}else{
			lateTime = "0:00";
		}

		return lateTime;
	}



}
