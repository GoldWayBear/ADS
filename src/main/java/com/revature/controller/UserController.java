package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.User;
import com.revature.service.UserService;

@RestController(value="userController")
@RequestMapping(path="/user")
@CrossOrigin(origins = "*")
public class UserController {

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(path ="/all")
	public List<User> getAllUsers(){
		return this.userService.getAllUsers();
		}
	
	@PostMapping(path = "/new", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void createNewUser(@RequestBody User user) {
		
		this.userService.createNewUser(user);
	}
}
