package com.uca.tfg.service;

import java.util.Collection;

import javax.annotation.PostConstruct;

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

	@PostConstruct
	public void init() {
		if (images.findAll().isEmpty()) {
			images.save(new Image("http://hdimages.org/wp-content/uploads/2017/03/placeholder-image1.gif"));
			images.save(new Image("http://via.placeholder.com/350x150"));
		}
	}

	@Override
	public Collection<Image> getAllImages() {
		return images.findAll();
	}

	@Override
	public ResponseEntity<Image> getImage(long id) {
		Image image = images.findOne(id);

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
		Image image = images.findOne(id);

		if (image != null) {
			images.delete(id);
			return new ResponseEntity<>(image, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
