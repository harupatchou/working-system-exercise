package main.java.kot.admin.setup.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.service.Service;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.dao.WorkingtypeDao;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.entity.LaborSystem;
import main.java.kot.entity.Workingtype;
import main.java.kot.logic.DataLogic;

public class WorkingTypeListService extends Service {

	@Override
	public void executeGet(HttpServletRequest req, HttpServletResponse resp) {

		//セッション情報取得
		HttpSession session=req.getSession();
		int loginId = (Integer) session.getAttribute("loginId");

		//従業員種別情報
		Employee employee = DataLogic.getEmployee(loginId);
		Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompany().getId());
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
