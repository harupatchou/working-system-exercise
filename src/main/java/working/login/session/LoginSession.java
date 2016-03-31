package main.java.working.login.session;

/**
 * セッション情報
 * @author kuro
 */
public class LoginSession {

	//ID
	private Integer login_id;
	//パスワード
	private String password;

	public Integer getLogin_id() {
		return login_id;
	}
	public void setLogin_id(Integer login_id) {
		this.login_id = login_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
