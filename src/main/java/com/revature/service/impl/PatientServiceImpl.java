package com.revature.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Address;
import com.revature.model.Appointment;
import com.revature.model.Bill;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.repository.impl.PatientRepositoryImpl;
import com.revature.service.PatientService;

@Service("patientService")
public class PatientServiceImpl implements PatientService {
	
	// Autowire to the patientRepository bean
	private PatientRepositoryImpl patientRepository;
	
	@Autowired
	public void setPatientRepository(PatientRepositoryImpl patientRepository) {
		this.patientRepository = patientRepository;
	}

	@Override
	public void registerNewPatient(User user, Address address) {
		
		// Assign the patient's role
//		Role role = new Role(3, "patient");
		
		// Send info to the repository layer
		patientRepository.registerNewPatient(user, address );

	}

	@Override
	public boolean userLogin(String email, String password) {
		
		// Initial boolean value
		boolean b = false;
		
		// Get the password that is stored in the DB
		String savedPassword = patientRepository.getPassword(email);
		
		// Check to see if the passwords match
		if(password.equals(savedPassword)) {
			
			// Change the value of b to true
			b = true;
			
		}
		
		return b;
	}

	@Override
	public void updateInfo(User user, Address address) {
		
		// Send info to the repository layer
		patientRepository.updateInfo(user, address);
		
	}

	@Override
	public List<Appointment> viewAvailability() {
		
		// Grab the info from the Repository layer
		List<Appointment> doctorsAvailability = patientRepository.viewAvailability();
		
		return doctorsAvailability;
	}

	@Override
	public void bookAppointment(Appointment appointment) {
		
		// Send info to the repository layer
		patientRepository.bookAppointment(appointment);
		
	}

	@Override
	public List<Appointment> getMyAppointments(User patient) {
		
		// Grab the info from the Repository layer
		List<Appointment> myAppointments = patientRepository.getMyAppointments(patient);
		
		return myAppointments;
	}

	@Override
	public void cancelAppointment(Appointment appointment) {
		
		// Set the status to canceled
		appointment.setStatus("canceled");
		
		// Send data to the repository layer
		patientRepository.cancelAppointment(appointment);
		
	}

	@Override
	public List<Bill> viewMyBills(User patient) {
		
		// Grab the info from the Repository layer
		List<Bill> myBills = patientRepository.viewMyBills(patient);
		
		return myBills;
	}

	@Override
	public void payBill(Bill bill) {
		
		// Send info to the repository layer
		patientRepository.payBill(bill);
		
	}

}
