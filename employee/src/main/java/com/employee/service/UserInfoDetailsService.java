package com.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.employee.config.UserInfoDetails;
import com.employee.entity.User;
import com.employee.repository.UserRepository;

public class UserInfoDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<User> user = this.userRepository.findByUsername(username);
		return user.map(UserInfoDetails::new).orElseThrow(()->new UsernameNotFoundException("User not found" + username));
		
	}

}
