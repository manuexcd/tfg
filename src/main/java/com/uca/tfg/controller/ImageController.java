package com.uca.tfg.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uca.tfg.dto.ImageDTO;
import com.uca.tfg.exception.ImageNotFoundException;
import com.uca.tfg.mapper.ImageMapper;
import com.uca.tfg.service.ImageService;

@RestController
@RequestMapping("/images")
public class ImageController {

	@Autowired
	private ImageMapper mapper;

	@Autowired
	private ImageService imageManager;

	@GetMapping(value = "/{id}")
	public ResponseEntity<ImageDTO> getImageById(@PathVariable String id) throws ImageNotFoundException {
		return Optional.ofNullable(imageManager.getImage(Long.valueOf(id)))
				.map(image -> new ResponseEntity<>(mapper.mapEntityToDto(image), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity<ImageDTO> addImage(@RequestBody ImageDTO dto) {
		return Optional.ofNullable(dto)
				.map(imageDto -> new ResponseEntity<>(
						mapper.mapEntityToDto(imageManager.addImage(mapper.mapDtoToEntity(imageDto))), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
