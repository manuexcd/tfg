package com.uca.tfg.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.uca.tfg.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration
@WebAppConfiguration
public class UserControllerTest {

	private MockMvc mvc;

	@MockBean
	private UserController userController;

	@Autowired
	private WebApplicationContext context;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	/*@Test
	@WithMockUser
	public void getAllUsers() throws Exception {
		given(userController.getAllUsers()).willReturn(Arrays.asList(new User()));
		mvc.perform(get("/users").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}*/

	@Test
	public void getAllUsersUnauthorized() throws Exception {
		given(userController.getAllUsers()).willReturn(Arrays.asList(new User()));
		mvc.perform(get("/users").contentType(APPLICATION_JSON)).andExpect(status().isUnauthorized());
	}

}
