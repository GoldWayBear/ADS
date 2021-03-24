/**
 * 
 */
package com.revature.model;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author Jinwei Xiong
 *
 */
public class RegisterRequest {
	@NotBlank
	private String username;
	@NotBlank
	private String password;	
	@NotBlank
	@Email
	private String email;	
	
	//private Set<String> role;
	private String role;
	
	public RegisterRequest() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}


}
