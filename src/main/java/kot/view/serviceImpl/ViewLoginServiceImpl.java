package main.java.kot.view.serviceImpl;

import main.java.kot.view.service.ViewLoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewLoginServiceImpl implements ViewLoginService{

	@Override
	public void viewLogin(HttpServletRequest req, HttpServletResponse resp){

		Integer reqParam = (Integer)req.getAttribute("reqParam");

		/* Get*/
		if(reqParam == 0){

			/* doGett側処理、今後追加する際はここに書く*/

		/* Post */
		}else{

			//初期化
			HttpSession session=req.getSession(true);
			//セッションのタイムアウト時間20分
			session.setMaxInactiveInterval(1200); //TODO なぜここでタイムアウト20分？？20分の意味は？

			String strUserId =req.getParameter("userId");
			Integer userId = Integer.parseInt(strUserId);

			String password  =req.getParameter("password");

			/* tex をセッションへ格納します。 */
			session.setAttribute("loginId",userId);
			session.setAttribute("password",password);

		}
	}

}
