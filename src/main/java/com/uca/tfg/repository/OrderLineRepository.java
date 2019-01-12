package com.uca.tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uca.tfg.model.OrderLine;

@Repository("OrderLineDAO")
public interface OrderLineRepository extends JpaRepository<OrderLine, Long>{

}
