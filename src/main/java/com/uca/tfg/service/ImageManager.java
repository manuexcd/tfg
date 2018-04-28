package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.uca.tfg.model.Image;

public interface ImageManager {
	public Collection<Image> getAllImages();

	public ResponseEntity<Image> getImage(long id);

	public Image addImage(Image image);

	public ResponseEntity<Image> deleteImage(long id);
}
