package com.revature.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.auth.jwts.JSONWebTokens;
import com.revature.auth.model.LoginRequest;
import com.revature.auth.model.RegisterRequest;
import com.revature.auth.model.RoleEnum;
import com.revature.auth.model.JSONWebTokenResponse;
import com.revature.auth.model.MessageResponse;
import com.revature.auth.repository.UserRepository;
import com.revature.auth.repository.RoleRepository;
import com.revature.auth.service.UserDetailsInDAS;
import com.revature.model.Role;
import com.revature.model.User;

/**
 * @author Jinwei Xiong
 *
 */


@CrossOrigin(origins="*", maxAge=3600)
//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired 
	JSONWebTokens jwts;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
		String username = loginRequest.getUsername();
		String password = loginRequest.getPassword();
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwts.generateJwtToken(authentication);
		
		UserDetailsInDAS userDetails = (UserDetailsInDAS)authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JSONWebTokenResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));
		
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest registerRequest){
		String username = registerRequest.getUsername();
		String email = registerRequest.getEmail();
		String password = registerRequest.getPassword();
		
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
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);

		String strRole = registerRequest.getRole();
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
					Role doctor = roleRepository.findByRole(RoleEnum.ROLE_DOCTOR)
							.orElseThrow(()->new RuntimeException("Error: Role is not found."));
					roles.add(doctor);

					break;
				default:
					Role adminRole = roleRepository.findByRole(RoleEnum.ROLE_PATIENT)
							.orElseThrow(()->new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
			}
		}
		user.setRoles(roles);
		userRepository.save(user);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	
}
