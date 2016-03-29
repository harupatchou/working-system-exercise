package main.java.kot.view.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import main.java.kot.common.service.Service;

public class ViewLoginService extends Service {

	@Override
	public void executeGet(HttpServletRequest req, HttpServletResponse resp) {

	}

	@Override
	public void executePost(HttpServletRequest req, HttpServletResponse resp) {

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
