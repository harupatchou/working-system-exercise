package main.java.kot.view.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewLoginService {

	/* ViewLoginSevlet委譲処理 */
	public void viewLogin(HttpServletRequest req, HttpServletResponse resp);

}
