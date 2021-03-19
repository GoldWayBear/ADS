package com.revature.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jinwei Xiong
 *
 */
@CrossOrigin(origins="*", maxAge=3600)
//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/test")
public class TestController {
	@GetMapping("/all")
	@PreAuthorize(("hasRole('PATIENT') or hasRole('DOCTOR') or hasRole('ADMIN')"))
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/patient")
	@PreAuthorize("hasRole('PATIENT')")
	public String patientAccess() {
		System.out.println("Now in Patient");
		return "patient Content return from DAS.";
	}
	
	@GetMapping("/doctor")
	@PreAuthorize("hasRole('DOCTOR')")
	public String doctorAccess() {
		System.out.println("Now in Doctor");
		return "doctor content return from DAS.";
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		System.out.println("Now in Admin");
		return "Admin content return from DAS.";
	}
}
