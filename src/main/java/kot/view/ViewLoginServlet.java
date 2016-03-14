package main.java.kot.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class ViewLoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//セッション破棄
		HttpSession session=req.getSession(true);
		session.invalidate();

		ServletContext application = req.getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher("/jsp/login/login.jsp");
		rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		//文字形式をUTF-8指定
		req.setCharacterEncoding("UTF-8");

		//初期化
		HttpSession session=req.getSession(true);
		//セッションのタイムアウト時間20分
		session.setMaxInactiveInterval(1200);

		String strUserId =req.getParameter("userId");
		Integer userId = Integer.parseInt(strUserId);

		String password  =req.getParameter("password");

		/* tex をセッションへ格納します。 */
		session.setAttribute("loginId",userId);
		session.setAttribute("password",password);

		resp.sendRedirect("/kot/login/check");
	}
}
