package main.java.kot.admin.setup.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.admin.setup.service.SetupService;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.Company;
import main.java.kot.entity.WorkingTime;

@WebServlet("/master/CompanyEdit")
public class CompanyEditServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");
		//セッション情報取得
		HttpSession session=req.getSession();
		Company userCompany = (Company) session.getAttribute("sesCompany");

		req.setAttribute("userCompany", userCompany);

		//勤怠時間関連取得
		List<AttendanceTime> attendanceTimeList = SetupService.getAttendanceTime(userCompany.getId());

		req.setAttribute("attendanceTimeList", attendanceTimeList);

		WorkingTime workingTime = new WorkingTime();

		//変形労働があるかどうか判別
		Integer count = 0;
		for(int i=0;i<attendanceTimeList.size();i++){
			if(attendanceTimeList.get(i).getLaborSystemId() == 2){
				count += 1;
				Integer irregularId = attendanceTimeList.get(i).getLaborSystemId();
				req.setAttribute("irregular", irregularId);
				//workingTimeを取得
				workingTime = SetupService.getWorkingTime(attendanceTimeList.get(i).getLaborSystemId());
			}
		}

		req.setAttribute("count", count);
		req.setAttribute("workingTime", workingTime);


		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/master/setup/companyEdit.jsp");

		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");
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
		SetupService.registCompany(company,userCompany);

		String strLaborSystemId =req.getParameter("laborSystemId");
		Integer laborSystemId = Integer.parseInt(strLaborSystemId);

		String attendanceTime  =req.getParameter("attendanceTime");
		String leaveTime =req.getParameter("leaveTime");

		//勤怠時間の登録
		AttendanceTime insertTime = new AttendanceTime();

		insertTime.setLaborSystemId(laborSystemId);
		insertTime.setStartTime(attendanceTime);
		insertTime.setEndTime(leaveTime);
		insertTime.setCompany(userCompany);

		SetupService.registAttendTime(insertTime);

		//もし変形労働ならば
		if(laborSystemId == 2){
			String strRegularTime =req.getParameter("regularTime");
			Double regularTime = Double.parseDouble(strRegularTime);

			//所定時間の登録
			WorkingTime insertWorkingTime = new WorkingTime();

			insertWorkingTime.setLaborSystemId(laborSystemId);
			insertWorkingTime.setWorkingTime(regularTime);

			SetupService.registWorkingTime(insertWorkingTime);
		}
		resp.sendRedirect("/kot/login");
	}

}
