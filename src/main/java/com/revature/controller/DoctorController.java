package com.revature.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Appointment;
import com.revature.model.PatientDoctor;
import com.revature.service.DoctorViewService;
import com.revature.service.impl.DoctorViewServiceImpl;

/*
 * Ok I have service layer and repository layer all mapped out and implemented.
 * What's next? Well let's start from controller to repository.
 * 
 * Controller: Instead of having an integer parameter we can an object containing the doctor's user id.
 * Service does not have much validation, we can improve security.
 * Repository is implemented.
 * Integration tests would determine the full functionality. Unit tests will be added after service layer is improved.
 * Also, let's not forget about exception handlers which are yet to be added.
 * 
 * */

@RestController(value = "doctorController")
@RequestMapping(path = "/doctor")
public class DoctorController {
	private DoctorViewService doctorViewService;
	
	@Autowired
	public void setDoctorViewService(DoctorViewServiceImpl doctorViewService) {
		this.doctorViewService = doctorViewService;
	}
	
	@GetMapping(path = "/viewSelfPatients", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<PatientDoctor> viewSelfPatients(@RequestParam int doctorId) {
		List<PatientDoctor> viewSelfPatients = null;
		viewSelfPatients = this.doctorViewService.viewSelfPatients(doctorId);
		return viewSelfPatients;
	}
	
	@GetMapping(path = "/viewBookedAppointments", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Appointment> viewBookedAppointments(@RequestParam int doctorId) {
		List<Appointment> viewBookedAppointments = null;
		viewBookedAppointments = this.doctorViewService.viewBookedAppointments(doctorId);
		return viewBookedAppointments;
	}

}
