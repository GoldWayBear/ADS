package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.repository.UserDAOImpl;
import com.revature.repository.UserRepository;
import com.revature.model.User;


@Service(value="userSrvice")
public class UserService {
	
	private UserRepository userRepository;
	@Autowired
	UserDAOImpl	userDAOImpl;
	
	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}
	public void createNewUser(User user) {
		//this.userRepository.save(user);
		userDAOImpl.save(user);
	}
}