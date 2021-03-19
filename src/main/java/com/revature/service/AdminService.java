package com.revature.service;

import java.util.List;

import com.revature.exception.NothingFoundException;
import com.revature.model.Address;
import com.revature.model.Appointment;
import com.revature.model.Bill;
import com.revature.model.User;

/**
 * @author sofka
 */

public interface AdminService {

	void createDoctor(Address address, User doctor);
	void createSpot(Appointment spot);
	void createBill(Bill bill);
	List<User> getAllDoctors() throws NothingFoundException;
	User getDoctorById(int id) throws NothingFoundException;
}
