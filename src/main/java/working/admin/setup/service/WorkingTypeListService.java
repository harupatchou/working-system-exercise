package main.java.working.admin.setup.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.working.common.service.Service;
import main.java.working.dao.EmployeeDao;
import main.java.working.dao.WorkingtypeDao;
import main.java.working.entity.Company;
import main.java.working.entity.Employee;
import main.java.working.entity.LaborSystem;
import main.java.working.entity.Workingtype;
import main.java.working.logic.DataLogic;

public class WorkingTypeListService extends Service {

	@Override
	public void executeGet(HttpServletRequest req, HttpServletResponse resp) {

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		//従業員種別情報
		Employee employee = DataLogic.getEmployee(loginId);
		Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompany().getId());
		System.out.println();
		company = DataLogic.getWorkingTypeOfCompany(company);

		req.setAttribute("workingtypeList", company.getWorkingtypeList());

	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) {

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

		LaborSystem laborSystem = new LaborSystem();
		laborSystem.setId(laborSystemId);
		workingtype.setLaborSystem(laborSystem);

		Company company = new Company();
		company.setId(companyId);

		workingtype.setCompany(company);

		WorkingtypeDao.registWorkingtype(workingtype);

	}

}
