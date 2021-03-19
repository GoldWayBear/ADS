package com.revature.repository.impl;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.model.Appointment;
import com.revature.model.PatientDoctor;
import com.revature.repository.DoctorViewRepository;
import com.revature.util.HibernateSessionFactory;

@Repository
public class DoctorViewRepositoryImpl implements DoctorViewRepository{

	@Override
	public List<PatientDoctor> viewSelfPatients(int doctorId) {
		List<PatientDoctor> viewSelfPatients = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			viewSelfPatients = session.createQuery("FROM PatientDoctor WHERE doctor.userId = :doctorId", PatientDoctor.class)
					.setParameter("doctorId", doctorId).getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
		}
				
		return viewSelfPatients;
	}

	@Override
	public List<Appointment> viewBookedAppointments(int doctorId) {
		List<Appointment> viewBookedAppointments = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			viewBookedAppointments = session.createQuery("FROM Appointment WHERE doctor.userId = :doctorId AND "
					+ "status = :status", Appointment.class).setParameter("doctorId", doctorId).setParameter("status", "available")
					.getResultList();
			transaction.commit();
		}catch(HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
		}
		return viewBookedAppointments;
	}

}
