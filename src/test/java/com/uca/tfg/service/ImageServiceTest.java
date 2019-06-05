package com.uca.tfg.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.uca.tfg.exception.ImageNotFoundException;
import com.uca.tfg.model.Image;
import com.uca.tfg.repository.ImageRepository;

@RunWith(MockitoJUnitRunner.class)
public class ImageServiceTest {
	
	@Mock
	private ImageRepository dao;
	
	@InjectMocks
	private ImageServiceImpl service;
	
	@Test
	public void testGetAllImages() {
		given(dao.findAll()).willReturn(Arrays.asList(new Image()));
		assertNotNull(service.getAllImages());
	}

	@Test
	public void testGetImageById() throws ImageNotFoundException {
		given(dao.findById(anyLong())).willReturn(Optional.of(new Image()));
		assertNotNull(service.getImage(anyLong()));
	}
	
	@Test
	public void testAddImage() {
		Image image = new Image();
		given(dao.save(image)).willReturn(image);
		assertNotNull(service.addImage(image));
	}
	
	@Test
	public void testDeleteImage() {
		Image image = new Image();
		dao.save(image);
		service.deleteImage(image.getId());
		assertFalse(dao.existsById(image.getId()));
	}
}
