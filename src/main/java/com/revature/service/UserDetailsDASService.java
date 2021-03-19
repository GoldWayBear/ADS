///**
// * 
// */
//package com.revature.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.revature.model.User;
//import com.revature.repository.UserDAO;
//import com.revature.repository.UserDAOImpl;
//
///**
// * @author Jinwei Xiong
// *
// */
//@Service
//public class UserDetailsDASService implements UserDetailsService{
//	@Autowired
//	UserDAOImpl userDao;
//	@Override
//	@Transactional
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		User user = userDao.findUserByUsername(username);
//		return UserDetailsDAS.build(user);
//	}
//
//}
