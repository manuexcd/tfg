package com.uca.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("UserDAO")
public interface UserDAO extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	
}
