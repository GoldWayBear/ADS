package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Address;
import com.revature.model.Appointment;
import com.revature.model.Bill;
import com.revature.model.User;
import com.revature.service.impl.PatientServiceImpl;

@RestController("patientController")
@RequestMapping(path = "/patient")
public class PatientController {

	// Autowire to the patientService bean
	private PatientServiceImpl patientService;
	
	@Autowired
	public void setPatientService(PatientServiceImpl patientService) {
		this.patientService = patientService;
	}
	
	// Endpoint for registering a new patient
	@PostMapping(path = "/new", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void registerNewPatient(@RequestBody User user, Address address) {
		this.patientService.registerNewPatient(user, address);
	}
	
	// Endpoint for patients to view doctors and their availability
	@GetMapping(path = "/availability", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Appointment> viewAvailability(){
		return this.patientService.viewAvailability();
	}
		
	// Endpoint for patients can book an appointment
	@PostMapping(path = "/booking", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void bookAppointment(@RequestBody Appointment appointment) {
		this.patientService.bookAppointment(appointment);
	}
		
	// Endpoint for patients to view their appointments
	@GetMapping(path = "/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Appointment> getMyAppointments(@RequestBody User patient){
		return this.getMyAppointments(patient);
	}
		
	// Endpoint for patients that want to cancel appointments
	@PostMapping(path = "/cancel", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void cancelAppointment(@RequestBody Appointment appointment) {
		this.patientService.cancelAppointment(appointment);
	}

	// Endpoint for patients that want to view their bills
	@GetMapping(path = "/bills", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Bill> viewMyBills(@RequestBody User patient){
		return this.patientService.viewMyBills(patient);
	}
		
	// Endpoint for patients to pay their bills
	@PostMapping(path = "/pay", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void payBill(@RequestBody Bill bill) {
		this.patientService.payBill(bill);
	}
	
}
