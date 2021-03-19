package com.revature.service;

import java.util.List;

import com.revature.model.Address;
import com.revature.model.Appointment;
import com.revature.model.Bill;
import com.revature.model.User;

public interface PatientService {

	// A new patient can register for a new account
	void registerNewPatient(User user, Address address);
	
	// Patients can login
	boolean userLogin(String email, String password);
	
	// Patients can update their info
	void updateInfo(User user, Address address);
	
	// Patients can view doctors and their availability
	List<Appointment> viewAvailability();
	
	// Patients can book an appointment
	void bookAppointment(Appointment appointment);
	
	// Patients can view their appointments
	List<Appointment> getMyAppointments(User patient);
	
	// Patients can cancel appointments
	void cancelAppointment(Appointment appointment);
	
	// Patients can view their bills
	List<Bill> viewMyBills(User patient);
	
	// Patients can pay their bills
	void payBill(Bill bill);
}
