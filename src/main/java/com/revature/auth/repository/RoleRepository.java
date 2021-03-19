/**
 * 
 */
package com.revature.auth.repository;

import org.springframework.stereotype.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.auth.model.RoleEnum;
import com.revature.model.Role;
/**
 * @author Jinwei Xiong
 *
 */
@Repository(value="roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer>{
	//Optional<Role> findAllByName(RoleEnum name);
	Optional<Role> findByRole(RoleEnum name);
}
