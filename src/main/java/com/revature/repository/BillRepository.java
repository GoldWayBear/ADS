package com.revature.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.revature.model.Bill;

@Repository(value = "billRepository")
public interface BillRepository extends JpaRepository<Bill, Integer> {

	// Send bill to the client and patients can pay their bills
	<S extends Bill> S saveAndFlush(Bill bill);
	
	// Patients can view their bills
	@Query(value = "SELECT b FROM Bill b WHERE b.user.userId = :userId")
	List<Bill> findAllByUser(@Param("userId") int userId);
	
	List<Bill> findAll();
	
}
