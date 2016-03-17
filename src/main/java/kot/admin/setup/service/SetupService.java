package main.java.kot.admin.setup.service;

import java.util.List;

import main.java.kot.dao.AttendanceTimeDao;
import main.java.kot.dao.CompanyDao;
import main.java.kot.entity.AttendanceTime;
import main.java.kot.entity.Company;

public class SetupService {

	//company情報の登録
	public static void registCompany(Company company,Company userCompany) {
		CompanyDao.editCompany(company,userCompany);
	}

	//company_idからcompany情報取得
	public static Company getCompany(Integer companyId) {
		return CompanyDao.getCompany(companyId);
	}

	//company_idからattendance_time情報取得
	public static List<AttendanceTime> getAttendanceTime(Integer id) {
		return AttendanceTimeDao.getAttendanceTime(id);
	}


}
