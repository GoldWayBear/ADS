package com.revature.repository.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.revature.exception.NothingFoundException;
import com.revature.model.Address;
import com.revature.model.Appointment;
import com.revature.model.Bill;
import com.revature.model.User;
import com.revature.repository.AdminRepository;
import com.revature.util.HibernateSessionFactory;

/**
 * @author sofka
 */

@Repository(value = "adminRepository")
public class AdminRepositoryImpl implements AdminRepository {
	
	public AdminRepositoryImpl() {
		
	}

	//--------------------------ADD NEW DOCTOR TO THE SYSTEM

	@Override // Create Employee (with Address) // Create Login
	public void createDoctor(Address address, User doctor) {
		

			//All my work is done within the context of a Hibernate session
			Session s = null;
			// The Transaction interface gives me control over  DB transactions.
			Transaction tx = null;
			
			try {
				s = HibernateSessionFactory.getSession();
				tx = s.beginTransaction();
				// This method persists the doctor,address and login (i.e. creates a new record in three tables)
				//s.save(address);
			
				s.save(doctor);
				tx.commit();

			}catch(HibernateException e) {
				e.printStackTrace();
				tx.rollback();

			}finally {
				//Always close your sessions!
				s.close();
			}

		}
	//Create new available for an appointment spot in doctor schedule
	@Override
	public void createSpot(Appointment spot) {
		//All my work is done within the context of a Hibernate session
		Session s = null;
		// The Transaction interface gives me control over  DB transactions.
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			// This method persists appointment(i.e. creates a new record in Appointment table)
			s.save(spot);
			tx.commit();

		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();

		}finally {
			//Always close your sessions!
			s.close();
		}
		
	}
	
	//Send bill to a client
	@Override
	public void createBill(Bill bill) {
		//All my work is done within the context of a Hibernate session
		Session s = null;
		// The Transaction interface gives me control over  DB transactions.
		Transaction tx = null;
		
		try {
			s = HibernateSessionFactory.getSession();
			tx = s.beginTransaction();
			// This method persists bill(i.e. creates a new record in Bill table)
			s.save(bill);
			tx.commit();

		}catch(HibernateException e) {
			e.printStackTrace();
			tx.rollback();

		}finally {
			//Always close your sessions!
			s.close();
		}
	}
	//See all doctors( before updating information)

	@Override
	public List<User> getAllDoctors() throws NothingFoundException {
		List<User> doctors = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			doctors = session.createQuery("FROM User u WHERE u.role.role = :doctor", User.class)
					.setParameter("doctor", "doctor")
					.getResultList();
			transaction.commit();
			//Throw new Exception and display a message instead of returning empty List to user
			if (doctors.isEmpty()) {
				throw new NothingFoundException("No doctors found");
			}
		}catch(HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		}finally {
			session.close();
		}
		return doctors;
	}

	@Override
	public User getDoctorById(int id) throws NothingFoundException {
		User doctor = null;
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSessionFactory.getSession();
			transaction = session.beginTransaction();
			doctor = session.createQuery("FROM User u WHERE u.userId = :id", User.class)
					.setParameter("id", id)
					.getSingleResult();
			transaction.commit();

		}catch(HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
		}finally {
			//Throw new Exception and display a message instead of returning empty List to user
			if (doctor == null) {
				throw new NothingFoundException("Doctor not found");
			}
			session.close();
		}
		return doctor;
	}
}
