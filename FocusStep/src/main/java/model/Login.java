package model;

public class Login {
	private String loginId;
	private String pass;
	
	public Login(String id, String pass) {
		this.loginId = id;
		this.pass = pass;
	}
	public String getLoginId() {
		return loginId;
	}
	public String getPass() {
		return pass;
	}
}
