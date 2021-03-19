package com.revature.repository;

import java.util.List;

import com.revature.exception.NothingFoundException;
import com.revature.model.Address;
import com.revature.model.Appointment;
import com.revature.model.Bill;
import com.revature.model.User;

/**
 * @author sofka
 */

public interface AdminRepository {
	
	//1.Log in
	
	//2.Register new doctor (add login and an address for him as well)
	void createDoctor(Address address,User doctor);
	
	//3.Update doctor information
	//Get all doctors first
	List<User> getAllDoctors() throws NothingFoundException;
	//Get one doctor
	User getDoctorById(int id) throws NothingFoundException;
	
	
	//4.Add new availabilities to schedule
	void createSpot(Appointment spot);
	
	//5.Send bill to client
	void createBill(Bill bill);
	
	//6.Send reminder e-mail to the client on the day of Appointment(optional)

}
