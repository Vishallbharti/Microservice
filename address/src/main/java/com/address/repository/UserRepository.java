package com.address.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.address.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	 public Optional<User> findByUsername(String username);
     public boolean existsByUsername(String username);
}
