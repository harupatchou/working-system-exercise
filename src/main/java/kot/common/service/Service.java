package main.java.kot.common.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class Service {
	/* Get処理*/
	abstract public void executeGet(HttpServletRequest req, HttpServletResponse resp);
	/* Post処理 */
	abstract public void executePost(HttpServletRequest req, HttpServletResponse resp);

}
