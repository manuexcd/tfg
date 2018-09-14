package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Image> getImage(long id) {
		Image image = images.findById(id).orElse(null);

		if (image != null)
			return new ResponseEntity<>(image, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public Image addImage(Image image) {
		images.save(image);

		return image;
	}

	@Override
	public ResponseEntity<Image> deleteImage(long id) {
		Image image = images.findById(id).orElse(null);

		if (image != null) {
			images.delete(image);
			return new ResponseEntity<>(image, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
