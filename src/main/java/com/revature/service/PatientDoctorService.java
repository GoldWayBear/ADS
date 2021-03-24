package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.PatientDoctor;
import com.revature.repository.PatientDoctorRepository;

@Service(value = "patientDoctorService")
public class PatientDoctorService {

	// Autowiring to the repository layer
	@Autowired
	private PatientDoctorRepository patientDoctorRepository;
	
	// Find all patients for a doctor
//	public List<PatientDoctor> findAllByDoctor(int doctorId){
//		return this.patientDoctorRepository.findAllByDoctorUserId(doctorId);
//	}
	
	// Create a new mapping between patient and doctor
	public void newRelationship(PatientDoctor patientDoctor) {
		this.patientDoctorRepository.save(patientDoctor);
	}
	
}
