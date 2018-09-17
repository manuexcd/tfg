package com.uca.tfg.service;

import java.util.Collection;

import com.uca.tfg.model.Image;

public interface ImageManager {
	public Collection<Image> getAllImages();

	public Image getImage(long id);

	public Image addImage(Image image);

	public void deleteImage(long id);
}
