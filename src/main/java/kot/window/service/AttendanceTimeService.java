package main.java.kot.window.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.admin.setup.logic.MasterSetupLogic;
import main.java.kot.common.service.Service;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.Company;
import main.java.kot.entity.LaborSystem;
import main.java.kot.entity.WorkingTime;

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
		if(laborSystemId.equals(LaborSystem.normalLaborSystem) || laborSystemId.equals(LaborSystem.deformationLaborSystem)){
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
