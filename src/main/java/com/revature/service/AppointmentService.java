package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Appointment;
import com.revature.repository.AppointmentRepository;
import com.revature.repository.UserDAOImpl;

@Service(value = "appointmentService")
public class AppointmentService {

	@Autowired
	UserDAOImpl userDAOImpl;
	
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}
	
	// Admin creates a new appointment spot
	public void createAppointment(Appointment appointment) {
		// Set the status to available
		appointment.setStatus("available");
		appointment.setPatient(null);
		//this.appointmentRepository.save(appointment);
		userDAOImpl.saveAppointment(appointment);
	}
	
	// Patients book an appointment
	public void bookAppointment(Appointment appointment) {
		// Set the status to booked
		appointment.setStatus("booked");
		//this.appointmentRepository.save(appointment);
		userDAOImpl.saveAppointment(appointment);
	}
	
	// Patients can cancel appointments
	public void cancelAppointment(Appointment appointment) {
		// Set the status to canceled
		appointment.setStatus("available");
		appointment.setPatient(null);

		//this.appointmentRepository.save(appointment);
		userDAOImpl.saveAppointment(appointment);
	}
	// PAdmin can view all appointments
	public List<Appointment> getAll(){
		return this.appointmentRepository.findAll();
	}
	
	// Patients can view available appointments
	public List<Appointment> viewAllAvailableAppointments(){
		return this.appointmentRepository.findAllByStatus();
	}
	
	// Patients can view their appointments
	public List<Appointment> viewMyAppointments(int patientId){
		return this.appointmentRepository.findAllByPatient(patientId);
	}
	
	// Doctor can view their scheduled appointments
	public List<Appointment> viewMyScheduledAppointments(int doctorId){
		return this.appointmentRepository.findAllByDoctorAndStatus(doctorId);
	}
	
}
