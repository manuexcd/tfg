package com.uca.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uca.tfg.model.User;

@Repository("UserDAO")
public interface UserDAO extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	
}
