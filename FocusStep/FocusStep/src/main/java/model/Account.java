package model;

import java.sql.Date;

public class Account {
	private int userId;
	private String pass;
	private String mail;
	private String loginId;
	private String nameSei;
	private String nameMei;
	private String nameSeiKana;
	private String nameMeiKana;
	private Date birth;
	

	public Account(int userId,String pass,String mail,String loginId,String nameSei,String nameMei, String nameSeiKana, String nameMeiKana, Date birth) {
	this.userId = userId;
	this.pass = pass;
	this.mail = mail;
	this.loginId = loginId;
	this.nameSei = nameSei;
	this.nameMei = nameMei;
	this.nameSeiKana = nameSeiKana;
	this.nameMeiKana = nameMeiKana;
	this.birth = birth;
	}

	
	public int getUserId() {
		return userId;
	}

	public String getPass() {
		return pass;
	}

	public String getMail() {
		return mail;
	}
	
	public String getLoginId() {
		return loginId;
	}
	
	public String getNameSei() {
		return nameSei;
	}

	public String getNameMei() {
		return nameMei;
	}

	public String getNameSeiKana() {
		return nameSeiKana;
	}

	public String getNameMeiKana() {
		return nameMeiKana;
	}
	
	public Date getBirth() {
		return birth;
	}
}
