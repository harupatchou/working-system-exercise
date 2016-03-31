package main.java.working.window.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.working.admin.setup.logic.MasterSetupLogic;
import main.java.working.common.service.Service;
import main.java.working.entity.AttendanceTime;
import main.java.working.entity.Company;
import main.java.working.entity.LaborSystem;
import main.java.working.entity.WorkingTime;

public class AttendanceTimeService extends Service {

	@Override
	public void executeGet(HttpServletRequest req, HttpServletResponse resp) {

		//セッション情報取得
		HttpSession session=req.getSession();
		Company userCompany = (Company) session.getAttribute("sesCompany");

		//勤怠時間関連取得
		List<AttendanceTime> attendanceTimeList = MasterSetupLogic.getAttendanceTime(userCompany.getId());

		Integer laborSystemId = Integer.parseInt(req.getParameter("laborSystemId"));

		//通常の場合、出勤時間と退勤時間を編集する画面
		if(laborSystemId.equals(LaborSystem.NORMAL_LABOR_SYSTEM) || laborSystemId.equals(LaborSystem.DEFORMATION_LABOR_SYSTEM)){
			//TODO 謎の-1処理
			String startTime = attendanceTimeList.get(laborSystemId-1).getStartTime();
			String endTime = attendanceTimeList.get(laborSystemId-1).getEndTime();

			req.setAttribute("startTime", startTime);
			req.setAttribute("endTime", endTime);
		}

		req.setAttribute("attendanceTimeList", attendanceTimeList);

		//workingTimeを取得
		WorkingTime workingTime = MasterSetupLogic.getWorkingTime(laborSystemId);

		req.setAttribute("workingTime", workingTime);

	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) {

	}

}
