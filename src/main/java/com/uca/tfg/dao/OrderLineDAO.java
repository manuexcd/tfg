package com.uca.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uca.tfg.model.OrderLine;

@Repository("OrderLineDAO")
public interface OrderLineDAO extends JpaRepository<OrderLine, Long>{

}
