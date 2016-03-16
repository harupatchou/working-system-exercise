package main.java.kot.admin.setup.service;

import main.java.kot.dao.CompanyDao;
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

}
