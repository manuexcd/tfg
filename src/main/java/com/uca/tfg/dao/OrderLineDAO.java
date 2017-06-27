package com.uca.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("OrderLineDAO")
public interface OrderLineDAO extends JpaRepository<OrderLine, Long>{

}
