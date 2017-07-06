package com.uca.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uca.tfg.model.Product;

@Repository("ProductDAO")
public interface ProductDAO extends JpaRepository<Product, Long>{

}
