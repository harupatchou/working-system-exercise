package main.java.kot.view.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ViewMasterService {

	/*ViewMasterServlet委譲処理*/
	public void viewMaster(HttpServletRequest req, HttpServletResponse resp);

}
