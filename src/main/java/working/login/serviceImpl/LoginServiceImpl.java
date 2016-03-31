package main.java.working.login.serviceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.working.common.service.ServiceConstant;
import main.java.working.dao.CompanyDao;
import main.java.working.dao.EmployeeDao;
import main.java.working.entity.Company;
import main.java.working.entity.Employee;
import main.java.working.login.service.LoginServise;
import main.java.working.login.session.LoginSession;

public class LoginServiceImpl implements LoginServise{

	//employee_idからログインチェック
	private static Employee LoginCheckInfo(Integer employeeId){
		return EmployeeDao.LoginCheckInfo(employeeId);
	}

	//employee_idからemployee情報取得
	private static Employee getSessionEmployee(Integer employeeId){
		return EmployeeDao.getEmployee(employeeId);
	}

	//company_idからcompany情報取得（紐付いている情報全て）
	private static Company getSessionCompany(Integer companyId) {
		return CompanyDao.getCompany(companyId);
	}

	@Override
	public String Login(HttpServletRequest req, HttpServletResponse resp){

		Integer reqParam = (Integer)req.getAttribute("reqParam");

		/* Get*/
		if(reqParam == ServiceConstant.GET_REQUEST){

			/* doGet側処理、今後追加する際はここに書く*/
			return null;

		/* Post */
		}else{

			HttpSession session=req.getSession(false);

			LoginSession sess = new LoginSession();

			sess.setLogin_id((Integer) session.getAttribute("loginId"));
			sess.setPassword((String) session.getAttribute("password"));

			Employee checkInf = LoginCheckInfo((Integer) session.getAttribute("loginId"));
			String url;

			if(checkInf.getEmployeeId() == null){
				//入力したidが存在しなければ
				url = "/working/login";
			}else{
				if(!checkInf.getPassword().equals(sess.getPassword())) {
					//passが一致していなければ
					url = "/working/login";
				}else{
					//一致していれば
					String userName = getSessionEmployee(sess.getLogin_id()).getFirstName();

					//従業員情報をセッションに格納
					Employee sesEmployee = getSessionEmployee(sess.getLogin_id());

					//会社情報をセッションに格納
					Company sesCompany = getSessionCompany(sesEmployee.getCompany().getId());

					session.setAttribute("userName", userName);
					session.setAttribute("sesEmployee",sesEmployee);
					session.setAttribute("sesCompany",sesCompany);

					if(checkInf.getCompany().getMasterId() == sess.getLogin_id()){
						//employeeIdが紐付いたCompanyのmasterIDと一致していればmaster画面へ
						//一致していなければemployee画面へ
						url = "/working/master/Top";
					}else{
						url = "/working/employee/Top";
					}
				}
			}
			return url;
		}
	}
}
