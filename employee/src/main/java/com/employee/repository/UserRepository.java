package com.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByEmail(String email);
	
	public boolean existsByEmail(String email);

}
