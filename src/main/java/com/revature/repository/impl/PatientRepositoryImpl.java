package com.revature.repository.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.model.Address;
import com.revature.model.Appointment;
import com.revature.model.Bill;
import com.revature.model.User;
import com.revature.repository.PatientRepository;
import com.revature.util.HibernateSessionFactory;

@Repository("patientRepository")
public class PatientRepositoryImpl implements PatientRepository {
	
	
	// Transaction variable
	Transaction tx;

	@Override
	public void registerNewPatient(User user, Address address) {

		// Try block that will start and automatically close a session
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Save the new patient info
			session.save(address);
			session.save(user);
			
			// Commit the transaction
			tx.commit();
			
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}

	}

	@Override
	public String getPassword(String email) {
		
		// Password variable
		String password = null;
		
		// Try block that will start and automatically close a session
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// create an initial User object
			User user = new User();
			
			// Query the DB and append it to the User object
			user = session.createQuery("FROM User WHERE email = :email", User.class)
					.setParameter("email", email)
					.getSingleResult();
			
			// Get the password
			password = user.getPassword();
			
			// Commit the transaction
			tx.commit();
			
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		return password;
	}

	@Override
	public void updateInfo(User user, Address address) {

		// Try block that will start and automatically close a session
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Save the new patient info
			session.update(address);
			session.update(user);
			
			// Commit the transaction
			tx.commit();
			
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}


	}

	@Override
	public List<Appointment> viewAvailability() {
		
		// Initialize a list object
		List<Appointment> doctorsAvailability = null;
		
		// Try block that will start and automatically close a session
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Query the DB and return the results as a list
			doctorsAvailability = session.createQuery("FROM Appointment WHERE status = open", Appointment.class)
					.getResultList();
			
			// Commit the transaction
			tx.commit();
			
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		return doctorsAvailability;
	}

	@Override
	public void bookAppointment(Appointment appointment) {
		
		// Try block that will start and automatically close a session
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Query the DB
			session.update(appointment);
			
			// Commit the transaction
			tx.commit();
			
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}

	}

	@Override
	public List<Appointment> getMyAppointments(User patient) {
		
		// Initialize a list object
		List<Appointment> myAppointments = null;
		
		// Try block that will start and automatically close a session
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Query the DB and return the results as a list
			myAppointments = session.createQuery("FROM Appointment WHERE patient = :patient", Appointment.class)
					.setParameter("patient", patient)
					.getResultList();
			
			// Commit the transaction
			tx.commit();
			
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		return myAppointments;

	}

	@Override
	public void cancelAppointment(Appointment appointment) {
		
		// Try block that will start and automatically close a session
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Save the new patient info
			session.update(appointment);
			
			// Commit the transaction
			tx.commit();
			
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}


	}

	@Override
	public List<Bill> viewMyBills(User patient) {
		
		// Initialize a list object
		List<Bill> myBills = null;
		
		// Try block that will start and automatically close a session
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Query the DB and return the results as a list
			myBills = session.createQuery("FROM Bill WHERE user = :patient", Bill.class)
					.setParameter("patient", patient)
					.getResultList();
			
			// Commit the transaction
			tx.commit();
			
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		
		return myBills;
	}

	@Override
	public void payBill(Bill bill) {
		
		// Try block that will start and automatically close a session
		try (Session session = HibernateSessionFactory.getSession()) {
			
			// Begin a transaction
			tx = session.beginTransaction();
			
			// Save the new patient info
			session.update(bill);
			
			// Commit the transaction
			tx.commit();
			
		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}


	}

}
