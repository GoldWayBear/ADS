package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.PatientDoctor;

@Repository(value = "patientDoctorRepository")
public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, Integer>{

	/*
	 * Find all patients for a doctor.
	 * Remember that doctor in the PatientDoctor entity is mapped to the Users entity.
	 * So if we do not want to pass in the entire user object for a doctor as a parameter,
	 * and just want the userId then we need to reference the users entity in the query.
	 */
//	@Query("SELECT pd FROM PatientDoctor pd WHERE pd.user.doctor.userId = :doctorId")
//	List<PatientDoctor> findAllByDoctorUserId(@Param("doctorId") int doctorId);
	
}
