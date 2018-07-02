package com.uca.tfg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;

public interface OrderDAO extends JpaRepository<Order, Long>{

	List<Order> findAllByOrderByOrderStatus();
	
	List<Order> findByParam(Long param);
	
	//@Query("SELECT o FROM Order o WHERE o.user = ?1")
	public List<Order> findByUser(User user);
}
