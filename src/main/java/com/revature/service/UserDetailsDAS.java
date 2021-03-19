///**
// * 
// */
//package com.revature.service;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.revature.model.User;
//
///**
// * @author Jinwei Xiong
// *
// */
//public class UserDetailsDAS implements UserDetails {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1898463148119160285L;
//
//	private int id;
//	private String username;
//	private String email;
//	@JsonIgnore
//	private String password;
//	private Collection<? extends GrantedAuthority> authorites;
//	
//	
//	public UserDetailsDAS(int id, String username, String email, String password,
//			Collection<? extends GrantedAuthority> authorites) {
//		super();
//		this.id = id;
//		this.username = username;
//		this.email = email;
//		this.password = password;
//		this.authorites = authorites;
//	}
//
//	public static UserDetailsDAS build(User user) {
//		// TODO Auto-generated constructor stub
//		List<GrantedAuthority> authorites = user.getRoles().stream()
//				.map(role->new SimpleGrantedAuthority(role.getRole().name()))
//				.collect(Collectors.toList());
//		
//		return new UserDetailsDAS(
//				user.getUserId(), 
//				user.getUsername(), 
//				user.getEmail(),
//				user.getPassword(), 
//				authorites);
//	}
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO Auto-generated method stub
//		return authorites;
//	}
//
//	public int getId() {
//		return id;
//	}
//	
//	public String getEmail() {
//		return email;
//	}
//	
//	@Override
//	public String getPassword() {
//		// TODO Auto-generated method stub
//		return password;
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO Auto-generated method stub
//		return username;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO Auto-generated method stub
//		return true;
//	}
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + id;
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		UserDetailsDAS other = (UserDetailsDAS) obj;
//		if (id != other.id)
//			return false;
//		return true;
//	}
//
//	
//}
