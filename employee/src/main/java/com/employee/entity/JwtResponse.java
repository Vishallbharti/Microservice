package com.employee.entity;

public class JwtResponse {
	private String token;
	private String username;

	public JwtResponse() {
		super();
	}

	public JwtResponse(String token, String username) {
		super();
		this.token = token;
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "JwtResponse [token=" + token + ", username=" + username + "]";
	}

}
