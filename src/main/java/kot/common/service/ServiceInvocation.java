package main.java.kot.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.admin.setup.service.MasterSetupService;
import main.java.kot.admin.setup.serviceImpl.MasterSetupServiceImpl;

public class ServiceInvocation {

	/* Serviceの呼び出し */
	public static void serviceInvocation(HttpServletRequest req, HttpServletResponse resp, Integer reqParam, Integer pageParam){
		req.setAttribute("reqParam", reqParam);
		MasterSetupService setupService = new MasterSetupServiceImpl();

		//pageParamと一致する処理の呼び出し
		if(pageParam == ServiceConstant.COMPANY_EDIT){
			setupService.companyEdit(req, resp);
		}else if(pageParam == ServiceConstant.EMPLOYEE_EDIT){
			setupService.employeeEdit(req, resp);
		}else if(pageParam == ServiceConstant.EMPLOYEE_LIST){
			setupService.employeeList(req, resp);
		}else if(pageParam == ServiceConstant.WORKING_TYPE){
			setupService.workingtypeList(req, resp);
		}
	}

}
