package main.java.kot.view.serviceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.SelectDate;
import main.java.kot.common.service.ServiceConstant;
import main.java.kot.dao.EmployeeDao;
import main.java.kot.entity.Company;
import main.java.kot.entity.Employee;
import main.java.kot.logic.DataLogic;
import main.java.kot.logic.DateLogic;
import main.java.kot.view.service.ViewMasterService;

public class ViewMasterServiceImpl implements ViewMasterService{

	@Override
	public void viewMaster(HttpServletRequest req, HttpServletResponse resp){

		Integer reqParam = (Integer)req.getAttribute("reqParam");

		/* Get*/
		if(reqParam == ServiceConstant.GET_REQUEST){

			//セッション情報取得
			HttpSession session=req.getSession();
			int loginId = (Integer) session.getAttribute("loginId");

			Employee employee = DataLogic.getEmployee(loginId);
			Company company = EmployeeDao.getEmployeeFromCompanyId(employee.getCompany().getId());
			//会社IDから年月取得
			SelectDate selectDate = DateLogic.employeeDateSummary(company);
			req.setAttribute("selectYear", selectDate.getYearList());
			req.setAttribute("selectMonth", selectDate.getMonthList());

		/* Post */
		}else if(reqParam == ServiceConstant.POST_REQUEST){

			/* doPost側処理、今後追加する際はここに書く*/

		}
	}

}
