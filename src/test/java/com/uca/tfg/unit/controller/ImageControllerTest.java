package com.uca.tfg.unit.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.uca.tfg.controller.ImageController;
import com.uca.tfg.model.Image;
import com.uca.tfg.service.ImageService;

@RunWith(MockitoJUnitRunner.class)
public class ImageControllerTest {

	private MockMvc mvc;

	@Mock
	private ImageService service;

	@InjectMocks
	private ImageController controller;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void testGetAllOrders() throws Exception {
		given(service.getImage(anyLong())).willReturn(new Image());
		mvc.perform(get("/images/1").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}
}
