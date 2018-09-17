package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.ImageDAO;
import com.uca.tfg.model.Image;

@Service("imageManager")
public class ImageManagerImp implements ImageManager {

	@Autowired
	private ImageDAO images;

	@Override
	public Collection<Image> getAllImages() {
		return images.findAll();
	}

	@Override
	public Image getImage(long id) {
		return images.findById(id).orElse(null);

	}

	@Override
	public Image addImage(Image image) {
		return images.save(image);
	}

	@Override
	public void deleteImage(long id) {
		images.deleteById(id);
	}
}
