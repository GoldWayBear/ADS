package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.PatientDoctor;
import com.revature.service.PatientDoctorService;

@RestController(value = "patientDoctorController")
@RequestMapping(path = "/patientdoctor")
@CrossOrigin(origins="*")
public class PatientDoctorController {

	// Autowiring in the service layer
	@Autowired
	private PatientDoctorService patientDoctorService;
	
	// Find all patients for a doctor
//	@GetMapping(path = "/viewSelfPatients", produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<PatientDoctor> findAllByDoctor(@RequestParam int doctorId){
//		return this.patientDoctorService.findAllByDoctor(doctorId);
//	}
	
	// Create a new mapping between patient and doctor
	@PostMapping(path = "/new-relationship", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void newRelationship(@RequestBody PatientDoctor patientDoctor) {
		this.patientDoctorService.newRelationship(patientDoctor);
	}
	
}
