package main.java.kot.logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.java.kot.common.Schedule;
import main.java.kot.common.SelectDate;
import main.java.kot.common.TempDate;
import main.java.kot.dao.WorkingAllDao;
import main.java.kot.employee.attendance.logic.AttendanceLogic;
import main.java.kot.entity.AttendanceStatus;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.entity.WorkingDay;
import main.java.kot.util.CalendarUtil;


public class DateLogic {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/*当日の日付取得*/
	public static TempDate getDate(Date date){

		TempDate tempDate = new TempDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		tempDate.setYear(cal.get(Calendar.YEAR));
		tempDate.setMonth(cal.get(Calendar.MONTH) + 1);
		tempDate.setDay(cal.get(Calendar.DAY_OF_MONTH));
		return tempDate;
	}

	/*月の日数取得*/
	public static Integer getMaxDay(Date date){

		int maxDay = 0;

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return maxDay;
	}


	/* 入力した時間からhour、minutesを取得 */
	public static String[] timeStr (String str){
		String[] timeStr = str.split(":");
		return timeStr;
	}


	/* 送られてきた二つのString[]を比較し、String型00:00表記の時間を返す */
	public static String getStringTime (String[] startStr,String[] endStr){

			Integer startHour = Integer.parseInt(startStr[0]);
			Integer endHour = Integer.parseInt(endStr[0]);
			Integer startMinute = Integer.parseInt(startStr[1]);
			Integer endMinute = Integer.parseInt(endStr[1]);

			String strTime = timeCalculate(startHour,endHour,startMinute,endMinute);

			return strTime;
	}

	/* 比較部分切り出し */
	public static String timeCalculate (Integer startHour,Integer endHour,Integer startMinute,Integer endMinute){

		//計算用integer型変数
		Integer minuteTime = 60;
		Integer hourTime = 0;

		/* 分計算 */
		if((endMinute - startMinute)<0){
			hourTime -= 1;
			minuteTime += endMinute - startMinute;
		}else{
			minuteTime = endMinute - startMinute;
		}

		/* 時間計算 */
		if((endHour - startHour)<0){
			hourTime += 24 - startHour;
		}else{
			hourTime += endHour - startHour;
		}

		String strHourTime = String.valueOf(hourTime);
		String strMinuteTime = String.valueOf(minuteTime);
		if(strMinuteTime.length() == 1) strMinuteTime = "0" + strMinuteTime;

		String time = strHourTime + ":" + strMinuteTime;

		return time;
	}

	/* 実労働算出 */
	public static String getCalculateTime (String allAttendTime,String breakTime){

		String[] allAttendStr = timeStr(allAttendTime);
		String[] breakTimeStr = timeStr(breakTime);

		Integer attendHour = Integer.parseInt(allAttendStr[0]);
		Integer attendMinute = Integer.parseInt(allAttendStr[1]);
		Integer breakHour = Integer.parseInt(breakTimeStr[0]);
		Integer breakMinute = Integer.parseInt(breakTimeStr[1]);

		String strTime = realCalculate(attendHour,breakHour,attendMinute,breakMinute);

		return strTime;

	}

	/* 実労働計算 */
	public static String realCalculate (Integer attendHour,Integer breakHour,Integer attendMinute,Integer breakMinute){

		//計算用integer型変数
		Integer minuteTime = 60;
		Integer hourTime = 0;

		/* 分計算 */
		if((attendMinute - breakMinute)<0){
			hourTime -= 1;
			minuteTime += attendMinute - breakMinute;
		}else{
			minuteTime = attendMinute - breakMinute;
		}

		/* 時間計算 */
		hourTime += attendHour - breakHour;

		String strHourTime = String.valueOf(hourTime);
		String strMinuteTime = String.valueOf(minuteTime);
		if(strMinuteTime.length() == 1) strMinuteTime = "0" + strMinuteTime;

		String time = strHourTime + ":" + strMinuteTime;

		return time;
	}


	/* Dataの変換 util→sql */
	public static java.sql.Date sqlDate (Date utilDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(utilDate);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		java.sql.Date sqlDate = new java.sql.Date(cal.getTimeInMillis());

		return sqlDate;
	}

	//画面に送信する情報をListに格納していく
	//TODO 出退勤時間等も入れていく.
	public static List<Schedule> getMonthlyDate (Integer year,Integer month,Integer day_count, Integer userId){

		//画面表示用に月の情報を格納するList
		List<Schedule> scheduleList = new ArrayList<Schedule>();

		//年月を固定なのでStringの変数に代入
		String strYear = String.valueOf(year);
		String strMonth =  String.valueOf(month);

		for (int i = 0;i < day_count; i++){
			List<String> addDate = new ArrayList<String>();
			Schedule tempSchedule = new Schedule();

			addDate.add(strYear);
			addDate.add(strMonth);
			addDate.add(String.valueOf(i+1));

			//当日のWorkingDayを取得
			WorkingDay tempWork = new WorkingDay();

			tempWork = AttendanceLogic.selectAllByEmployeeId(year, month,i+1, userId);

			if(tempWork.getId() == null){
				tempSchedule.setEnterStatus("未入力");
				tempWork.setStatusCode(4);

				AttendanceStatus tempAttend = new AttendanceStatus();

				tempAttend.setId(4);
				tempAttend.setStatusName("未入力");

				tempWork.setAttendanceStatus(tempAttend);
			}else{
				tempSchedule.setEnterStatus("入力済");

				//もし欠勤ならば初期化して画面に表示する際に0:00を表示しない
				if(tempWork.getStatusCode() == 2){
					tempWork = new WorkingDay();
					AttendanceStatus tempAttend = new AttendanceStatus();

					tempWork.setStatusCode(2);
					//欠勤情報のみ表示
					tempAttend.setId(2);
					tempAttend.setStatusName("欠勤");

					tempWork.setAttendanceStatus(tempAttend);
				}
			}

			tempSchedule.setWorkingDay(tempWork);

			Schedule weekInfo = CalendarUtil.getWeek(year,month,i+1);

			tempSchedule.setMonthlyDate(GeneralLogic.joinString(addDate, "/"));
			tempSchedule.setWeekStr(weekInfo.getWeekStr());
			tempSchedule.setWeekNum(weekInfo.getWeekNum());
			tempSchedule.setHolidayFlag(weekInfo.getHolidayFlag());
			if(tempSchedule.getHolidayFlag() == 0){
				tempSchedule.setStrHoliday("通常");
			}else{
				if(tempSchedule.getWeekNum()==1){
					tempSchedule.setStrHoliday("法定休日");
				}else{
					tempSchedule.setStrHoliday("所定休日");
				}
			}

			//休憩時間算出
			if(tempWork.getStatusCode() == 1){
				//計算用
				String[] tempBreakStartTime = DateLogic.timeStr(tempWork.getBreakTimeStart());
				String[] tempBreakEndTime = DateLogic.timeStr(tempWork.getBreakTimeEnd());

				tempSchedule.setBreakTime(DateLogic.getStringTime(tempBreakStartTime,tempBreakEndTime));
			}

			scheduleList.add(tempSchedule);
		}
		return scheduleList;

	}

	//0000/0/0形式のDateを0000/00/00の形式に変換
	public static String formatDate (String oldDate){

		String[] arrayDate = oldDate.split("/");
		String formatDate = arrayDate[0];

		for (int i=0;i<arrayDate.length-1;i++){
			if(arrayDate[i+1].length() == 1){
				formatDate += "/0" + arrayDate[i+1];
			} else {
				formatDate += "/" + arrayDate[i+1];
			}
		}
		return formatDate;
	}
	//会社に所属する従業員リストのSelectDateの合算から重複
	public static SelectDate employeeDateSummary(Company company){
		List<Employee> employeeList = company.getEmployeeList();

		SelectDate selectDate= new SelectDate();
		List<Integer> yearList = new ArrayList<>();
		List<Integer> monthList = new ArrayList<>();

		for(int i = 0;i < employeeList.size(); i++){
			int employeeId = employeeList.get(i).getEmployeeId();
			SelectDate tempSelectDate = WorkingAllDao.getYearAndMonth(employeeId);
			List<Integer> tempYearList = tempSelectDate.getYearList();
			List<Integer> tempMonthList = tempSelectDate.getMonthList();
			for(int j = 0; j < tempYearList.size(); j++){
				yearList.add(tempYearList.get(j));
			}
			for(int k = 0; k < tempMonthList.size(); k++){
				monthList.add(tempMonthList.get(k));
			}

		}
		//重複削除してセット
		selectDate.setYearList(ArrayListLogic.duplicateDelete(yearList));
		selectDate.setMonthList(ArrayListLogic.duplicateDelete(monthList));

		return selectDate;
	}

	//0:00形式のStringを00:00の形式に変換
	public static String formatTime (String oldTime){

		String[] arrayTime = oldTime.split(":");
		String formatTime ="";

		if(arrayTime[0].length() == 1){
			formatTime += "0" + arrayTime[0] + ":";
		} else {
			formatTime += arrayTime[0] + ":";
		}

		if(arrayTime[1].length() == 1){
			formatTime += "0" + arrayTime[1];
		} else {
			formatTime += arrayTime[1];
		}

		return formatTime;
	}

	//00:00形式のStringを0:00の形式に変換
		public static String formatTimeForServerSide (String oldTime){

			String[] arrayTime = oldTime.split(":");

			String firstChar = arrayTime[0].substring(0, 1);

			//時の先頭が0のときは0以降の文字列を取得(「00:00」→「0:00」)
			if(firstChar.equals("0")){
				arrayTime[0] = arrayTime[0].substring(1);
			}

			String formatTime = arrayTime[0] + ":" + arrayTime[1];

			return formatTime;
		}
}
