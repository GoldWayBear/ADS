/**
 * 
 */
package com.revature.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.auth.repository.UserRepository;
import com.revature.model.User;

/**
 * @author Jinwei Xiong
 *
 */
@Service
public class UserDetailsServiceInDAS implements UserDetailsService{
	@Autowired
	UserRepository userRepository;
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username)
				.orElseThrow(()->new UsernameNotFoundException("User Not Found with username: " + username));
		return UserDetailsInDAS.build(user);
	}
}
