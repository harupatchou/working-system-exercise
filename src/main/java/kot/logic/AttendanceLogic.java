package main.java.kot.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import main.java.kot.common.AttendanceData;
import main.java.kot.common.InsertDay;
import main.java.kot.common.Schedule;
import main.java.kot.common.StrTime;
import main.java.kot.employee.attendance.service.AttendanceServise;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.Overtime;
import main.java.kot.entity.WorkingAll;
import main.java.kot.entity.WorkingDay;

public class AttendanceLogic {

	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	// 日付の取得
	public static InsertDay setInsertDay(HttpServletRequest req,AttendanceData attendanceData) {
		InsertDay insertDay = new InsertDay();
		List<String> strInsertDay = new ArrayList<String>();

		// 画面から得た日付
		insertDay.setDay(Integer.parseInt(req.getParameter("day")));
		insertDay.setDayStr(req.getParameter("day"));
		insertDay.setMonth(Integer.parseInt(req.getParameter("month")));
		insertDay.setMonthStr(req.getParameter("month"));
		insertDay.setYear(Integer.parseInt(req.getParameter("year")));
		insertDay.setYearStr(req.getParameter("year"));

		// string型のinsertする形式にするために使用
		strInsertDay.add(insertDay.getYearStr());
		strInsertDay.add(insertDay.getMonthStr());
		strInsertDay.add(insertDay.getDayStr());

		// サーバに登録するための日付をinsertDayにset
		insertDay.setInsertDay(GeneralLogic.joinString(strInsertDay, "-"));

		return insertDay;
	}

	// 時間の取得
	public static StrTime setStrTime(HttpServletRequest req,AttendanceData attendanceData) {
		// working_day Tableにinsert
		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		// working_day.insert用
		String breakStartTime = req.getParameter("breakStartTime");
		String breakEndTime = req.getParameter("breakEndTime");

		// 入力がなければ00:00を入れる
		if (startTime.equals("")) {
			startTime = "0:00";
			endTime = "0:00";
			breakStartTime = "0:00";
			breakEndTime = "0:00";
		}

		// 00:00形式を0:00形式に
		startTime = DateLogic.formatTimeForServerSide(startTime);
		endTime = DateLogic.formatTimeForServerSide(endTime);

		// 計算用
		String[] tempBreakStartTime = DateLogic.timeStr(breakStartTime);
		String[] tempBreakEndTime = DateLogic.timeStr(breakEndTime);
		// 00:00形式に変換
		String[] startTimeStr = DateLogic.timeStr(startTime);
		String[] endTimeStr = DateLogic.timeStr(endTime);

		StrTime strTime = new StrTime();

		strTime.setStartTime(startTime);
		strTime.setEndTime(endTime);
		strTime.setBreakStartTime(breakStartTime);
		strTime.setBreakEndTime(breakEndTime);
		strTime.setArrayStartTime(startTimeStr);
		strTime.setArrayEndTime(endTimeStr);
		strTime.setArrayBreakStartTime(tempBreakStartTime);
		strTime.setArrayBreakEndTime(tempBreakEndTime);

		// 一日の休憩時間算出
		String breakTime = DateLogic.getStringTime(strTime.getArrayBreakStartTime(),strTime.getArrayBreakEndTime());

		strTime.setBreakTime(breakTime);

		return strTime;
	}

	public static WorkingDay setWorkingDay(HttpServletRequest req,AttendanceData attendanceData) {
		WorkingDay workingDay = new WorkingDay();

		//TODO 法定休日判別 現状は法定休日が土曜なためweekNumが1だが、設定で変更できるように変更すべき？
		if((attendanceData.getSchedule().getHolidayFlag() == Schedule.HOLIDAY) && (attendanceData.getSchedule().getWeekNum() == 1)){
			workingDay.setLegalFlag(WorkingDay.HOLIDAY_WORK);
		}else{
			workingDay.setLegalFlag(WorkingDay.WEEKDAY_WORK);
		}

		//勤怠ステータス判別
		String strStatusCode = req.getParameter("attend_status");
		Integer statusCode = Integer.parseInt(strStatusCode);

		try {
			workingDay.setDate(sdf.parse(attendanceData.getInsertDay().getInsertDay()));
			workingDay.setWeek(attendanceData.getSchedule().getWeekNum());
			workingDay.setEmployeeId(attendanceData.getEmployee().getEmployeeId());
			workingDay.setStatusCode(statusCode);
			//ステータスで場合分け
			if(statusCode == 1){
				//もし出勤ならば
				workingDay.setAttendanceTime(attendanceData.getStrTime().getStartTime());
				workingDay.setLeaveTime(attendanceData.getStrTime().getEndTime());
				workingDay.setBreakTimeStart(attendanceData.getStrTime().getBreakStartTime());
				workingDay.setBreakTimeEnd(attendanceData.getStrTime().getBreakEndTime());
				//TODO 決め打ち
				workingDay.setNapTime("0:00");
			//TODO 欠勤有給の場合
			}else{
				workingDay.setAttendanceTime("0:00");
				workingDay.setLeaveTime("0:00");
				workingDay.setBreakTimeStart("0:00");
				workingDay.setBreakTimeEnd("0:00");

				//TODO 決め打ち
				workingDay.setNapTime("0:00");
			}
			// ここまで
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return workingDay;

	}

	public static WorkingAll setWorkingAll(AttendanceData attendanceData) {
		//勤怠時間関連取得
		AttendanceTime attendanceTime = AttendanceServise.selectAttendTime(attendanceData.getEmployee(),attendanceData.getEmployee().getWorkingType().getLaborSystemId());

		StrTime strTime = new StrTime();
		Overtime overtime = new Overtime();
		WorkingAll workingAll = new WorkingAll();

		//規定出勤時間
		String provisionAttendTime = attendanceTime.getStartTime();
		//規定退勤時間
		//String provisionLeaveTime = attendanceTime.getEndTime();

		//種別毎に必要な処理
		if(attendanceData.getWorkingtype().getLaborSystemId() == 1){
			/* 通常労働制ならば */
			//遅刻計算
			strTime.setLateTime(LateLogic.lateCheckForNormal(provisionAttendTime,attendanceData.getStrTime().getStartTime()));
		}else if(attendanceData.getWorkingtype().getLaborSystemId() == 2){
			//遅刻計算
			strTime.setLateTime(LateLogic.lateCheckForNormal(provisionAttendTime,attendanceData.getStrTime().getStartTime()));
		//フレックス用
		}

		//一日の総労働時間算出
		String attendDayAll = DateLogic.getStringTime(attendanceData.getStrTime().getArrayStartTime(),attendanceData.getStrTime().getArrayEndTime());

		//一日の実労働時間算出
		String attendDay= DateLogic.getCalculateTime(attendDayAll,attendanceData.getStrTime().getBreakTime());

		workingAll.setWorkingTimeAll(attendDay);

		//遅刻時間
		String lateTime = "0:00";

		//TODO 深夜 今は決め打ち
		String nightTime = "0:00";
		String nightOvertime = "0:00";

		overtime = setOverTime(attendanceData);

		//TODO 休日出勤
		//休日ならば
		if(attendanceData.getSchedule().getHolidayFlag()==1){
			//法定休日ならば
			if(attendanceData.getWorkingDay().getLegalFlag()==1){
				workingAll.setDayStatus("法定");
			}else{
				//TODO 土曜出勤,祝日出勤は何として扱うか
				workingAll.setDayStatus("所定");
			}
		}else{
			workingAll.setDayStatus("通常");
		}

		try {
			workingAll.setDate(sdf.parse(attendanceData.getInsertDay().getInsertDay()));
			workingAll.setWeek(attendanceData.getSchedule().getWeekNum());
			workingAll.setLegalOvertimeAll(overtime.getLegalOvertime());
			workingAll.setStatutoryOverTimeAll(overtime.getStatutoryOvertime());
			workingAll.setLateTimeAll(lateTime);
			workingAll.setEmployeeId(attendanceData.getEmployee().getEmployeeId());
			//TODO 決め打ち
			workingAll.setNightTimeAll(nightTime);
			workingAll.setNightOvertimeAll(nightOvertime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return workingAll;
	}

	public static Overtime setOverTime(AttendanceData attendanceData) {
		//残業にinsertするためにworkingDayTableのIDを取得
		WorkingDay insertDayInfo =AttendanceServise.selectByDayAndEmployeeId(attendanceData.getInsertDay().getInsertDay(), attendanceData.getEmployee().getEmployeeId());

		Overtime overtime = new Overtime();

		//種別毎に必要な処理
		if(attendanceData.getWorkingtype().getLaborSystemId() == 1){
			overtime = OvertimeLogic.getOvertime(attendanceData.getWorkingDay());
		}else if(attendanceData.getWorkingtype().getLaborSystemId() == 2){
			overtime = OvertimeLogic.getIrregularWorkingHourSystemOvertime(attendanceData.getWorkingDay());
		//フレックス用
		}else if(attendanceData.getWorkingtype().getLaborSystemId() == 3){
			overtime = OvertimeLogic.getFlexTimeOvertime(attendanceData.getWorkingDay());
		}

		//出勤を押した日のidを残業のdailyIdと紐付け
		overtime.setDailyId(insertDayInfo.getId());

		return overtime;
	}
}
