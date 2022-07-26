package com.example.web_client.models;

public class TokenAccess {

	private static String token;
	
	public TokenAccess() {
		super();
	};
	
	public TokenAccess(String token) {
		TokenAccess.token = token;	
	}
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		TokenAccess.token = token;
	}
	
	
}
