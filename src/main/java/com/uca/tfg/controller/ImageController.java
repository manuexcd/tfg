package com.uca.tfg.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.tfg.exception.ImageNotFoundException;
import com.uca.tfg.model.Image;
import com.uca.tfg.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

	@Autowired
	private ImageService imageManager;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Image> getImageById(@PathVariable String id) throws ImageNotFoundException {
		return Optional.ofNullable(imageManager.getImage(Long.valueOf(id)))
				.map(image -> new ResponseEntity<>(image, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
