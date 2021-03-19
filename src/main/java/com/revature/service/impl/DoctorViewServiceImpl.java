package com.revature.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Appointment;
import com.revature.model.PatientDoctor;
import com.revature.repository.DoctorViewRepository;
import com.revature.repository.impl.DoctorViewRepositoryImpl;
import com.revature.service.DoctorViewService;


@Service(value = "doctorViewService")
public class DoctorViewServiceImpl implements DoctorViewService{
	private DoctorViewRepository doctorViewRepository;
	
	@Autowired
	public void setDoctorViewRepository(DoctorViewRepositoryImpl doctorViewRepository) {
		this.doctorViewRepository = doctorViewRepository;
	}

	@Override
	public List<PatientDoctor> viewSelfPatients(int doctorId) {
		List<PatientDoctor> viewSelfPatients = null;
		viewSelfPatients = this.doctorViewRepository.viewSelfPatients(doctorId);
		return viewSelfPatients;
	}

	@Override
	public List<Appointment> viewBookedAppointments(int doctorId) {
		List<Appointment> viewBookedAppointments = null;
		viewBookedAppointments = this.doctorViewRepository.viewBookedAppointments(doctorId);
		return viewBookedAppointments;
	}

}
