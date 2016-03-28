package main.java.kot.window.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.admin.setup.logic.MasterSetupLogic;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.Company;
import main.java.kot.entity.WorkingTime;
import main.java.kot.window.service.AttendanceTimeService;

public class AttendanceTimeServiceImpl implements AttendanceTimeService {

	@Override
	public void attendanceTime(HttpServletRequest req, HttpServletResponse resp) {

		Integer reqParam = (Integer)req.getAttribute("reqParam");

		/* Get*/
		if(reqParam == 0){

			//セッション情報取得
			HttpSession session=req.getSession();
			Company userCompany = (Company) session.getAttribute("sesCompany");

			//勤怠時間関連取得
			List<AttendanceTime> attendanceTimeList = MasterSetupLogic.getAttendanceTime(userCompany.getId());

			Integer laborSystemId = Integer.parseInt(req.getParameter("laborSystemId"));

			//通常の場合、出勤時間と退勤時間を編集する画面
			if(laborSystemId == 1 || laborSystemId == 2){
				String startTime = attendanceTimeList.get(laborSystemId-1).getStartTime();
				String endTime = attendanceTimeList.get(laborSystemId-1).getEndTime();

				req.setAttribute("startTime", startTime);
				req.setAttribute("endTime", endTime);
			}

			req.setAttribute("attendanceTimeList", attendanceTimeList);

			//workingTimeを取得
			WorkingTime workingTime = MasterSetupLogic.getWorkingTime(laborSystemId);

			req.setAttribute("workingTime", workingTime);

		/* Post */
		}else{

			/* doPost側処理、今後追加する際はここに書く*/

		}
	}

}
