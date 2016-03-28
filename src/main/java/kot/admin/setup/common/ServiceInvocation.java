package main.java.kot.admin.setup.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.kot.admin.setup.service.MasterSetupService;
import main.java.kot.admin.setup.serviceImpl.MasterSetupServiceImpl;

public class ServiceInvocation {

	//処理呼び出し用定数
	private static final int companyEdit = 1;
	private static final int employeeEdit = 2;
	private static final int employeeList = 3;
	private static final int workingType = 4;

	/* Serviceの呼び出し */
	public static void serviceInvocation(HttpServletRequest req, HttpServletResponse resp, Integer reqParam, Integer pageParam){
		req.setAttribute("reqParam", reqParam);
		MasterSetupService setupService = new MasterSetupServiceImpl();

		//pageParamと一致する処理の呼び出し
		if(pageParam == companyEdit){
			setupService.companyEdit(req, resp);
		}else if(pageParam == employeeEdit){
			setupService.employeeEdit(req, resp);
		}else if(pageParam == employeeList){
			setupService.employeeList(req, resp);
		}else if(pageParam == workingType){
			setupService.workingtypeList(req, resp);
		}
	}

}
