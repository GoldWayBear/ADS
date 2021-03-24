package com.revature.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Address;

@Repository(value = "addressRepository")
public interface AddressRepository extends JpaRepository<Address, Integer> {

	/*
	 * This method will be used for the following features
	 * 1. Create a new address record for when a new patient/doctor are created
	 * 2. Update the address of the user
	 */
	<S extends Address> S save(Address address);
	
}
