package com.uca.tfg.unit.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uca.tfg.controller.ImageController;
import com.uca.tfg.dto.ImageDTO;
import com.uca.tfg.mapper.ImageMapperImpl;
import com.uca.tfg.model.Image;
import com.uca.tfg.service.ImageService;

@RunWith(MockitoJUnitRunner.class)
public class ImageControllerTest {

	private MockMvc mvc;

	@Mock
	private ImageService service;
	
	@Mock
	private ImageMapperImpl mapper;

	@InjectMocks
	private ImageController controller;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGetImage() throws Exception {
		given(service.getImage(anyLong())).willReturn(new Image());
		mvc.perform(get("/images/1").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testAddImage() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		given(service.addImage(any())).willReturn(new Image());
		mvc.perform(post("/images").content(obj.writeValueAsString(new ImageDTO())).contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
