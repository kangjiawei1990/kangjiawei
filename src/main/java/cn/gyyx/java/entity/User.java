package cn.gyyx.java.entity;

import java.io.Serializable;

public class User implements Serializable {
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	private String passWord;
	private String userName;
}
