package main.java.kot.admin.setup.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.dao.EmployeeDao;
import main.java.kot.dao.WorkingtypeDao;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.entity.WorkingTime;
import main.java.kot.entity.Workingtype;
import main.java.kot.logic.DataLogic;

public class MasterSetupServiceImpl implements MasterSetupService{

	@Override
	public void employeeList(HttpServletRequest req, HttpServletResponse resp){

		Integer reqParam = (Integer)req.getAttribute("reqParam");

		/* Get*/
		if(reqParam == 0){

			//セッション情報取得
			HttpSession session=req.getSession();
			int loginId = (Integer) session.getAttribute("loginId");

			//従業員情報
			Employee employee = DataLogic.getEmployee(loginId);
			Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompanyId());

			req.setAttribute("employeeList", company.getEmployeeList());

		/* Post */
		}else{

			/*現状なにもなし、今後追加する際はここに書く*/

		}

	}

	@Override
	public void workingtypeList(HttpServletRequest req, HttpServletResponse resp){

		Integer reqParam = (Integer)req.getAttribute("reqParam");

		/* Get */
		if(reqParam == 0){

			//セッション情報取得
			HttpSession session=req.getSession();
			int loginId = (Integer) session.getAttribute("loginId");

			//従業員種別情報
			Employee employee = DataLogic.getEmployee(loginId);
			Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompanyId());
			company = DataLogic.getWorkingTypeOfCompany(company);

			req.setAttribute("workingtypeList", company.getWorkingtypeList());

		/* Post */
		}else{

			Workingtype workingtype = new Workingtype();

			String workingName =req.getParameter("workingName");

			String stringWorkingtypeId =req.getParameter("workingtypeId");
			Integer workingtypeId = Integer.parseInt(stringWorkingtypeId);

			String stringLaborSystemId =req.getParameter("laborSystemId");
			Integer laborSystemId = Integer.parseInt(stringLaborSystemId);
			/* TODO 決め打ち */
			Integer companyId = 1;

			workingtype.setId(workingtypeId);
			workingtype.setWorkingName(workingName);
			workingtype.setLaborSystemId(laborSystemId);
			workingtype.setCompanyId(companyId);

			WorkingtypeDao.registWorkingtype(workingtype);

		}
	}

	@Override
	public void epmloyeeEdit(HttpServletRequest req, HttpServletResponse resp){

		Integer reqParam = (Integer)req.getAttribute("reqParam");

		/* Get*/
		if(reqParam == 0){

			//セッション情報取得
			HttpSession session=req.getSession();
			int loginId = (Integer) session.getAttribute("loginId");

			//従業員種別リスト
			Employee employee = DataLogic.getEmployee(loginId);
			Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompanyId());
			company = DataLogic.getWorkingTypeOfCompany(company);

			req.setAttribute("workingtypeList", company.getWorkingtypeList());

			//従業員情報
			Employee tempEmployee = new Employee();

			String strEmployeeId =req.getParameter("employeeId");
			if(strEmployeeId != null){
				Integer employeeId = Integer.parseInt(strEmployeeId);
				tempEmployee = DataLogic.getEmployee(employeeId);
			}

			req.setAttribute("employee", tempEmployee);

		/* Post */
		}else{

			//セッション情報取得
			HttpSession session=req.getSession();
			int loginId = (Integer) session.getAttribute("loginId");

			//従業員種別リスト
			Employee sessEmployee = DataLogic.getEmployee(loginId);

			Employee employee = new Employee();

			String firstName =req.getParameter("firstName");
			String lastName =req.getParameter("lastName");
			String strEmployeeId = req.getParameter("employeeId");
			Integer employeeId = Integer.parseInt(strEmployeeId);
			String strWorkingtypeId = req.getParameter("workingtypeId");
			Integer workingtypeId = Integer.parseInt(strWorkingtypeId);
			String password = req.getParameter("password");

			employee.setFirstName(firstName);
			employee.setLastName(lastName);
			employee.setEmployeeId(employeeId);
			employee.setWorkingTypeId(workingtypeId);
			employee.setPassword(password);
			employee.setCompanyId(sessEmployee.getCompanyId());

			//従業員登録
			SetupService.registEmployee(employee);

		}
	}

	@Override
	public void companyEdit(HttpServletRequest req, HttpServletResponse resp){

		Integer reqParam = (Integer)req.getAttribute("reqParam");

		/* Get*/
		if(reqParam == 0){

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

		/* Post */
		}else{

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

		}
	}
}
