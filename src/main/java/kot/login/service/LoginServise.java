package main.java.kot.login.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginServise{

	/*LoginSevlet委譲処理*/
	public String Login(HttpServletRequest req, HttpServletResponse resp);
}
