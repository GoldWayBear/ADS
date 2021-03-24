package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.Appointment;


@Repository(value = "appointmentRepository")
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	/*
	 * This method will be used for the following features:
	 * 1. Admin creates a new appointment spot
	 * 2. Patients book appointments
	 * 3. Patients cancel appointments
	 */
	<S extends Appointment> S save(Appointment appointment);
	
	// Admin can view all  appointments
	List<Appointment> findAll();
	
	// Patients can view all available appointments
	@Query(value = "SELECT a FROM Appointment a WHERE a.status = 'available'")
	List<Appointment> findAllByStatus();
	
	// Patients can view all their appointments
	@Query(value = "SELECT a FROM Appointment a WHERE a.patient.userId = :patientId AND a.status = 'booked'")
	List<Appointment> findAllByPatient(@Param("patientId") int patientId);
	
	// Doctors can view their booked appointments
	@Query(value = "SELECT a FROM Appointment a WHERE a.doctor.userId = :doctorId "
			+ "AND a.status = 'booked'")
	List<Appointment> findAllByDoctorAndStatus(@Param("doctorId") int doctorId);
	
}
