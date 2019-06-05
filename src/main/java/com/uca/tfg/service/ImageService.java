package com.uca.tfg.service;

import java.util.Collection;

import com.uca.tfg.exception.ImageNotFoundException;
import com.uca.tfg.model.Image;

public interface ImageService {
	public Collection<Image> getAllImages();

	public Image getImage(long id) throws ImageNotFoundException;

	public Image addImage(Image image);

	public void deleteImage(long id);
}
