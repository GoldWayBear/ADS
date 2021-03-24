package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Bill;
import com.revature.model.User;
import com.revature.service.BillService;

@RestController(value="billController")
@RequestMapping(path="/bill")
@CrossOrigin(origins = "*")
public class BillController {

	
	private BillService billService;
	
	@Autowired
	public void setBillService(BillService billService) {
		this.billService = billService;
	}
	
	@GetMapping(path ="/all")
	public List<Bill> getAllBills(){
		return this.billService.getAllBills();
		}
	
	@PostMapping(path = "/new", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public void createNewUser(@RequestBody Bill bill) {
		
		this.billService.createNewBill(bill);
	}
}
