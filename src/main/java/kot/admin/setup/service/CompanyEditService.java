package main.java.kot.admin.setup.service;

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

public class CompanyEditService extends Service {

	@Override
	public void executeGet(HttpServletRequest req, HttpServletResponse resp) {

		//セッション情報取得
		HttpSession session=req.getSession();
		Company userCompany = (Company) session.getAttribute("sesCompany");

		req.setAttribute("userCompany", userCompany);

		//勤怠時間関連取得
		List<AttendanceTime> attendanceTimeList = MasterSetupLogic.getAttendanceTime(userCompany.getId());

		req.setAttribute("attendanceTimeList", attendanceTimeList);

		WorkingTime workingTime = new WorkingTime();

		//変形労働があるかどうか判別
		Integer count = 0;
		for(int i=0;i<attendanceTimeList.size();i++){
			if(attendanceTimeList.get(i).getLaborSystem().getId().equals(LaborSystem.DEFORMATION_LABOR_SYSTEM)){
				count += 1;
				Integer irregularId = attendanceTimeList.get(i).getLaborSystem().getId();
				req.setAttribute("irregular", irregularId);
				//workingTimeを取得
				workingTime = MasterSetupLogic.getWorkingTime(attendanceTimeList.get(i).getLaborSystem().getId());
			}
		}

		req.setAttribute("count", count);
		req.setAttribute("workingTime", workingTime);
	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) {

		//セッション情報取得
		HttpSession session=req.getSession();
		Company userCompany = (Company) session.getAttribute("sesCompany");

		Company company = new Company();

		String stringCompanyId =req.getParameter("companyId");
		Integer companyId = Integer.parseInt(stringCompanyId);

		String companyName  =req.getParameter("companyName");

		company.setId(companyId);
		company.setCompanyName(companyName);

		//companyの登録
		MasterSetupLogic.registCompany(company,userCompany);

		String strLaborSystemId =req.getParameter("laborSystemId");
		Integer laborSystemId = Integer.parseInt(strLaborSystemId);

		String attendanceTime  =req.getParameter("attendanceTime");
		String leaveTime =req.getParameter("leaveTime");

		//勤怠時間の登録
		AttendanceTime insertTime = new AttendanceTime();

		LaborSystem laborSystem = new LaborSystem();
		laborSystem.setId(laborSystemId);
		insertTime.setLaborSystem(laborSystem);
		insertTime.setStartTime(attendanceTime);
		insertTime.setEndTime(leaveTime);
		insertTime.setCompany(userCompany);

		MasterSetupLogic.registAttendTime(insertTime);

		//もし変形労働ならば
		if(laborSystemId.equals(LaborSystem.DEFORMATION_LABOR_SYSTEM)){
			String strRegularTime =req.getParameter("regularTime");
			Double regularTime = Double.parseDouble(strRegularTime);

			//所定時間の登録
			WorkingTime insertWorkingTime = new WorkingTime();


			LaborSystem tempLabor = new LaborSystem();
			tempLabor.setId(laborSystemId);
			insertWorkingTime.setLaborSystem(laborSystem);
			insertWorkingTime.setWorkingTime(regularTime);

			MasterSetupLogic.registWorkingTime(insertWorkingTime);
		}
	}
}
