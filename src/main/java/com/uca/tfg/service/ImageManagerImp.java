package com.uca.tfg.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uca.tfg.exception.ImageNotFoundException;
import com.uca.tfg.model.Image;
import com.uca.tfg.repository.ImageRepository;

@Service("imageManager")
public class ImageManagerImp implements ImageManager {

	@Autowired
	private ImageRepository images;

	@Override
	public Collection<Image> getAllImages() {
		return images.findAll();
	}

	@Override
	public Image getImage(long id) throws ImageNotFoundException {
		return images.findById(id).orElseThrow(ImageNotFoundException::new);

	}

	@Override
	public Image addImage(Image image) {
		return Optional.ofNullable(image).map(newImage -> images.save(newImage))
				.orElseThrow(IllegalArgumentException::new);
	}

	@Override
	public void deleteImage(long id) {
		images.deleteById(id);
	}
}
