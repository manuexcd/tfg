package com.uca.tfg.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.Arrays;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.dao.ImageDAO;
import com.uca.tfg.model.Image;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImageServiceTest {
	
	@MockBean
	private ImageDAO dao;
	
	@Autowired
	private ImageManager service;
	
	@Test
	public void testGetAllImages() {
		given(dao.findAll()).willReturn(Arrays.asList(new Image()));
		assertNotNull(service.getAllImages());
	}

	@Test
	public void testGetImageById() {
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
