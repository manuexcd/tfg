package com.uca.tfg.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.uca.tfg.model.User;

@Repository("UserDAO")
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);
	
	public Page<User> findByOrderByName(Pageable page);
	
	@Query("SELECT u FROM User u WHERE u.name LIKE %?1% OR u.surname LIKE %?1% OR u.email LIKE %?1% OR u.address LIKE %?1% OR u.phone LIKE %?1%")
	public List<User> findByParam(String param);
}