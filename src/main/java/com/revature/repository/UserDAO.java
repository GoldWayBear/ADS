/**
 * 
 */
package com.revature.repository;

import java.util.List;

import com.revature.model.Address;
import com.revature.model.Appointment;
import com.revature.model.Bill;
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

	public User save(User user);
	public boolean updateUser(User user);
	Bill saveBill(Bill bill);
	Appointment saveAppointment(Appointment appointment);
	Address saveAddress(Address addr);
}
