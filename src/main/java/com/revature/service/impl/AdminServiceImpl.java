package com.revature.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.exception.NothingFoundException;
import com.revature.model.Address;
import com.revature.model.Appointment;
import com.revature.model.Bill;
import com.revature.model.User;
import com.revature.repository.AdminRepository;
import com.revature.repository.impl.AdminRepositoryImpl;
import com.revature.service.AdminService;

/**
 * @author sofka
 */

@Service(value = "adminService")
public class AdminServiceImpl implements AdminService{
	
	//field injection
//	@Autowired //can not test
//	private AdminRepositoryImpl adminRepository;
	
	private AdminRepository adminRepository;
	
	//constructor injection
//	public AdminServiceImpl(AdminRepository adminReporitory) {
//		this.adminRepository = adminRepository;
//	}
//	
	//Setter injection
	@Autowired
	public void setAdminRepository(AdminRepositoryImpl adminRepository) {
		this.adminRepository = adminRepository;
	}


	//Creating new doctor by passing Role #2 to createUser method
	@Override
	public void createDoctor(Address address, User doctor) {
		
//		Role role = new Role(3, " ");
//		doctor.setRole(role);

		this.adminRepository.createDoctor(address, doctor);
		
	}

	// Creating appointment with status "available" and user = null
	@Override
	public void createSpot(Appointment spot) {

		this.adminRepository.createSpot(spot);
		
	}

	@Override
	public void createBill(Bill bill) {

		this.adminRepository.createBill(bill);
		
	}


	@Override
	public List<User> getAllDoctors() throws NothingFoundException {
		List<User> doctors = null;
	
			doctors = adminRepository.getAllDoctors();
	

		return doctors;
	}


	@Override
	public User getDoctorById(int id)  throws NothingFoundException {
		User doctor = null;

			doctor = adminRepository.getDoctorById(id);
	
		return doctor;
	}

}
