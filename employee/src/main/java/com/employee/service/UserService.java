package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.User;
import com.employee.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public Optional<User> getUser(String username) {
		return this.userRepository.findByUsername(username);
	}

	public List<User> getAllUsers(){
		return this.userRepository.findAll();
	}

	public User addUser(User user) throws Exception {
		User newUser = new User();
		if (this.userRepository.existsByUsername(user.getUsername())) {
			throw new Exception("User is already exist!");
		} else {
			newUser = this.userRepository.save(user);
		}
		return newUser;

	}

}