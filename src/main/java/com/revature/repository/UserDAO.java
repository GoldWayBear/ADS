/**
 * 
 */
package com.revature.repository;

import java.util.List;

import com.revature.model.User;


/**
 * @author Jinwei Xiong
 *
 */
public interface UserDAO {
	public List<User> findAllUsers();
	public List<User> findUsersByUsername(String username);
	
	public User findUserById(int userId);
	public User findUserByUsername(String username);
	public User findUserByEmail(String email);

	public int addUser(User user);
	public boolean updateUser(User user);
}
