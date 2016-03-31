package main.java.working.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService{

	/*LoginServlet委譲処理*/
	public String Login(HttpServletRequest req, HttpServletResponse resp);
}
