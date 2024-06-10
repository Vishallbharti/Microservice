package com.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.entity.User;
import com.employee.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User createUser(User user) {
        return userRepository.save(user);
    }

    // Read operation
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    // Update operation
    public User updateUser(Integer id, User newUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setPassword(newUser.getPassword());
                    user.setEmail(newUser.getEmail());
                    user.setName(newUser.getName());
                    return userRepository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return userRepository.save(newUser);
                });
    }

    // Delete operation
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

}
