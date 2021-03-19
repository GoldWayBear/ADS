/**
 * 
 */
package com.revature.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.User;


/**
 * @author Jinwei Xiong
 *
 */
@Repository(value="userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAll();
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	
	<S extends User> S save(User user);
	@Query(value = "select u from User u where u.email = :email")
	List<User> findByEmail(@Param("email") String email);	
}
