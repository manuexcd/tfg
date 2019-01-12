package com.uca.tfg.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;

public interface OrderRepository extends JpaRepository<Order, Long>{

	public Page<Order> findAllByOrderByOrderStatus(Pageable page);
	
	public Page<Order> findAllByOrderByDate(Pageable page);
	
	public Page<Order> findByUser(User user, Pageable page);
}