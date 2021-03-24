/**
 * 
 */
package com.revature.model;

import javax.validation.constraints.*;


/**
 * @author Jinwei Xiong
 *
 */
public class LoginRequest {
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	public LoginRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
