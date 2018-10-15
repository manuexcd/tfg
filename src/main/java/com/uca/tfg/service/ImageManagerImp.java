package com.uca.tfg.service;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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
			images.save(new Image(
					"https://images.vexels.com/media/users/3/145908/preview2/52eabf633ca6414e60a7677b0b917d92-fabricante-de-avatar-masculino.jpg"));
			images.save(new Image(
					"https://images.vexels.com/media/users/3/145922/preview2/eb6591b54b2b6462b4c22ec1fc4c36ea-fabricante-de-avatar-femenino.jpg"));
			images.save(new Image(
					"https://psmedia.playstation.com/is/image/psmedia/ps4-listing-thumb-01-ps4-eu-06sep16?$Icon$"));
			images.save(
					new Image("https://e00-elmundo.uecdn.es/assets/multimedia/imagenes/2018/08/27/15353813396346.jpg"));
			images.save(
					new Image("https://www.nintendo.com/switch/assets/images/switch/buy-now/bundle_color_console.jpg"));
		}
	}

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
