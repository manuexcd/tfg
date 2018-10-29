package com.uca.tfg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uca.tfg.model.Image;

@Repository("ImageDAO")
public interface ImageRepository extends JpaRepository<Image, Long> {

}
