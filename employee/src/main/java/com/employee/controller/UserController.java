package com.employee.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.entity.User;
import com.employee.service.UserService;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@GetMapping("/all")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> getAllUsers() {
		System.out.println(this.userService.getAllUsers());
		return this.userService.getAllUsers().stream().filter(user -> user.getRole().equals("ROLE_USER"))
				.collect(Collectors.toList());
	}

	// Get user by id
	@GetMapping("user/{username}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Optional<User> getAdminByUsername(@PathVariable String username) {
		return this.userService.getUser(username);
	}

	// Get user by id
	@GetMapping("admin/{username}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public Optional<User> getUserByUsername(@PathVariable String username) {
		return this.userService.getUser(username);
	}

	// create new user
	@PostMapping("/createUser")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public User createUser(@RequestBody User user) throws Exception {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return this.userService.addUser(user);
	}

	@PostMapping("/addUser")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public User addUser(@RequestBody User user) throws Exception {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		return this.userService.addUser(user);
	}

}