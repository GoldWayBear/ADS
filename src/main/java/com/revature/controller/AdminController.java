package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.exception.NothingFoundException;
import com.revature.model.Address;
import com.revature.model.Appointment;
import com.revature.model.Bill;
import com.revature.model.User;
import com.revature.service.AdminService;

/**
 * @author sofka
 */

@RestController(value = "adminController")
@RequestMapping(path = "/admin")
public class AdminController {

	private AdminService adminService;
	
	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@PostMapping(path = "/new-doctor", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void createDoctor(@RequestBody Address address, User doctor) {
		this.adminService.createDoctor(address, doctor);
	}
	
	@PostMapping(path = "/new-bill", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void createBill(@RequestBody Bill bill) {
		this.adminService.createBill(bill);
	}
	
	@PostMapping(path = "/new-spot", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void createSpot(@RequestBody Appointment spot) {
		this.adminService.createSpot(spot);
	}
	
	@GetMapping(path = "/all-doctors", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getAllDoctors() throws NothingFoundException {
		List<User> doctors = null;
		doctors = this.adminService.getAllDoctors();
		return doctors;
	}
	@GetMapping(path = "/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
	public User getDoctorById(@RequestParam int id) throws NothingFoundException {
		User doctor = null;
		doctor = this.adminService.getDoctorById(id);
		return doctor;
	}
	
	@ExceptionHandler(NothingFoundException.class)
	public String handleException() {
		return "Sorry, There is nothing to display!";
	}
	
	
}
