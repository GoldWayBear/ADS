package com.revature.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import javax.servlet.annotation.MultipartConfig;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.auth.jwts.JSONWebTokens;
import com.revature.auth.model.LoginRequest;
import com.revature.auth.model.RegisterRequest;
import com.revature.auth.model.RoleEnum;
import com.revature.auth.model.JSONWebTokenResponse;
import com.revature.auth.model.MessageResponse;
import com.revature.repository.UserRepository;
import com.revature.repository.RoleRepository;
import com.revature.auth.service.UserDetailsInDAS;
import com.revature.model.Role;
import com.revature.model.User;
import com.revature.repository.UserDAOImpl;

/**
 * @author Jinwei Xiong
 *
 */


@CrossOrigin(origins="*", maxAge=3600)
//@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/auth")
//@MultipartConfig
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
	
	@Autowired 
	UserDAOImpl userDAOImpl;
	
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
	
	@PostMapping(path = "/register" )	
	public ResponseEntity<MessageResponse> registerUser(@RequestParam("image") MultipartFile image, @RequestParam("user") String user) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        User userobject = mapper.readValue(user, User.class);
        
		String username = userobject.getUsername();
		String email = userobject.getEmail();
		String password = userobject.getPassword();
		
		System.out.println("Name : " + image.getName());
        System.out.println("Type : " + image.getContentType());
        System.out.println("Name : " + image.getOriginalFilename());
        System.out.println("Size : " + image.getSize());
        
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

		String strRole = userobject.getRole();
		
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
		newUser.setRoles(roles);
		newUser.setRole(strRole);
		newUser.setProfilepicture(image.getBytes());
		
		//userRepository.save(newUser);
		userDAOImpl.save(newUser);
		
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
	

	  @PostMapping("/upload")
	  public ResponseEntity<MessageResponse> uploadFile(@RequestParam("image") MultipartFile image,
			  @RequestParam("user") String user) {
	    String message = "";
	    try {    	
	        ObjectMapper mapper = new ObjectMapper();
	        User userobject = mapper.readValue(user, User.class);
	       
	    	System.out.println("username : " + userobject.getUsername());
			 
	    	System.out.println("Name : " + image.getName());
	        System.out.println("Type : " + image.getContentType());
	        System.out.println("Name : " + image.getOriginalFilename());
	        System.out.println("Size : " + image.getSize());

	      message = "Uploaded the file successfully: " + image.getOriginalFilename();
	      return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
	    } catch (Exception e) {
	      message = "Could not upload the file: " + image.getOriginalFilename() + "!";
	      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
	    }
	  }
	  
		@GetMapping(path = "/all")
		public List<User> getAllUsers(){
			 List<User> users=  this.userRepository.findAll();
			 return users;
		}
}
