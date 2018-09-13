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
			images.save(new Image("https://cdn.hobbyconsolas.com/sites/navi.axelspringer.es/public/styles/main_element/public/media/image/2018/01/ps4-pro.jpg"));
			images.save(new Image("https://images-na.ssl-images-amazon.com/images/G/30/kindle/dp/2015/KM/muscat-fam_stripe_icon-d-es-250x170._CB514233316_.png"));
			images.save(new Image("https://images-na.ssl-images-amazon.com/images/I/61qL1jERsiL._SL1200_.jpg"));
			images.save(new Image("https://ep01.epimg.net/elpais/imagenes/2018/06/20/icon/1529482734_084519_1529489313_noticia_normal_recorte1.jpg"));
			images.save(new Image("https://amp.businessinsider.com/images/5b2d2faa1ae66235008b4705-750-563.jpg"));
			images.save(new Image("https://thumbs.dreamstime.com/b/bot%C3%B3n-acertado-de-avatar-del-hombre-emoci%C3%B3n-masculina-feliz-84653510.jpg"));
		}
	}

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
