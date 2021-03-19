package com.revature.service.impl.test;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.revature.model.Appointment;
import com.revature.model.PatientDoctor;
import com.revature.repository.impl.DoctorViewRepositoryImpl;
import com.revature.service.impl.DoctorViewServiceImpl;

public class DoctorViewServiceImplTest {
	@InjectMocks
	private static DoctorViewServiceImpl doctorViewServiceImpl;
	
	@Mock
	private DoctorViewRepositoryImpl doctorViewRepositoryImpl;
	
	@BeforeClass
	public static void setup() {
		doctorViewServiceImpl = new DoctorViewServiceImpl();
	}
	
	@Before
	public void beforeSetup() {
		MockitoAnnotations.initMocks(this);
		int doctorId = 0;
		List<PatientDoctor> testPatients = new ArrayList<>();
		List<Appointment> testAppointments = new ArrayList<>();
	}

	/*@Test
	public void testViewSelfPatientsForNoResultsException() {
		List<PatientDoctor> testPatients = new ArrayList<>();
		int doctorId = 1;
		try {
			Mockito.when(doctorViewRepositoryImpl.viewSelfPatients(doctorId)).thenReturn(testPatients);
			Assert.assertThrows(NoResultException.class, () -> doctorViewServiceImpl.viewSelfPatients(doctorId));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void testViewSelfPatientsForInvalidSession() {
		fail("Not yet implemented");
	}

	/*@Test
	public void testViewBookedAppointmentsForNoResultsException() {
		List<Appointment> testAppointments = new ArrayList<>();
		int doctorId = 1;
		try {
			Mockito.when(doctorViewRepositoryImpl.viewBookedAppointments(doctorId)).thenReturn(testAppointments);
			Assert.assertThrows(NoResultException.class, () -> doctorViewServiceImpl.viewSelfPatients(doctorId));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
	@Test
	public void testViewBookedAppointmentsForInvalidSession() {
		fail("Not yet implemented");
	}

}
