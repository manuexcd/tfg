package com.uca.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uca.tfg.model.Order;

public interface OrderDAO extends JpaRepository<Order, Long>{

}
