package com.revature.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.auth.model.MessageResponse;
import com.revature.auth.model.RoleEnum;
import com.revature.repository.RoleRepository;
import com.revature.repository.UserRepository;
import com.revature.repository.AddressRepository;
import com.revature.repository.UserDAOImpl;

import com.revature.exception.NothingFoundException;
import com.revature.model.Address;
import com.revature.model.Appointment;
import com.revature.model.Bill;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.service.AdminService;

/**
 * @author sofka
 */

@RestController(value = "adminController")
@RequestMapping(path = "/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	AddressRepository addrRepository;
	
	private AdminService adminService;
	
	@Autowired
	UserDAOImpl userDAOImpl;
	
	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@PostMapping(path = "/new-doctor", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<MessageResponse> createDoctor(@RequestBody User doctor) {
		System.out.println(doctor.getEmail());
		Address addr = doctor.getAddress();
		//addr = addrRepository.save(addr);
		addr = userDAOImpl.saveAddress(addr);
		
		String username = doctor.getUsername();
		String email = doctor.getEmail();
		String password = doctor.getPassword();
		
		//System.out.println(username+", "+email+", "+password);
		if (userRepository.existsByUsername(username)) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(email)) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setEmail(email);
		newUser.setPassword(password);
		newUser.setAddress(addr);
		newUser.setFirstName(doctor.getFirstName());
		newUser.setLastName(doctor.getLastName());
		newUser.setDob(doctor.getDob());
		newUser.setPhone(doctor.getPhone());
		newUser.setGender(doctor.getGender());

		String strRole = doctor.getRole();
		
		Set<Role> roles = new HashSet<>();		
		if (strRole == null || strRole.isEmpty() ) {
			Role userRole = roleRepository.findByRole(RoleEnum.ROLE_PATIENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			switch (strRole) {
				case "admin":
					Role patient = roleRepository.findByRole(RoleEnum.ROLE_ADMIN).orElseThrow(()->new RuntimeException("Error: Role is not found."));
					roles.add(patient);

					break;
				case "doctor":
					Role doctorrole = roleRepository.findByRole(RoleEnum.ROLE_DOCTOR)
							.orElseThrow(()->new RuntimeException("Error: Role is not found."));
					roles.add(doctorrole);

					break;
				default:
					Role adminRole = roleRepository.findByRole(RoleEnum.ROLE_PATIENT)
							.orElseThrow(()->new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
			}
		}
		newUser.setRoles(roles);
		newUser.setRole(strRole);	
		
		//userRepository.save(newUser);
		userDAOImpl.save(newUser);
		
		return ResponseEntity.ok(new MessageResponse("Doctor registered successfully!"));
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
