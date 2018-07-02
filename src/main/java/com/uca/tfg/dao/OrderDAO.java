package com.uca.tfg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;

public interface OrderDAO extends JpaRepository<Order, Long>{

	List<Order> findAllByOrderByOrderStatus();
	
	List<Order> findAllByOrderByDate();
	
	public List<Order> findByUser(User user);
}