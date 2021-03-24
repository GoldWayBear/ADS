
package com.revature.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.User;


@Repository(value="userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAll();
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
	// Adding a new patient or doctor
	// Updating existing patient or doctor
	<S extends User> S save(User user);
	@Query(value = "select u from User u where u.email = :email")
	List<User> findByEmail(@Param("email") String email);	
	
	// Grab the user
	 User findByUserId(int userId);
	 
	 // Get all doctors
	 @Query(value = "SELECT u FROM User u WHERE u.role = 'doctor'")
	 List<User> findAllByRoles();
}
