package com.uca.tfg.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uca.tfg.model.Image;

@Repository("ImageDAO")
public interface ImageDAO extends JpaRepository<Image, Long> {

}
