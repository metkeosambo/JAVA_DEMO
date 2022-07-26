package com.example.web_client.models;



import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	@JsonProperty("username")
	private String userName;
	
	@JsonProperty("password")

	private String password;
	
	public User() {
		super();
	}
	public User(String userName,String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
