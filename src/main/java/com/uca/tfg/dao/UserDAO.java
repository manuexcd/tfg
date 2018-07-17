package com.uca.tfg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uca.tfg.model.User;

@Repository("UserDAO")
public interface UserDAO extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	
	public List<User> findByOrderByName();
	
	@Query("SELECT u FROM User u WHERE u.name LIKE %?1% OR u.surname LIKE %?1% OR u.email LIKE %?1% OR u.address LIKE %?1% OR u.phone LIKE %?1%")
	public List<User> findByParam(String param);
}