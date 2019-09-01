package com.uca.tfg.unit.controller;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
import com.uca.tfg.controller.UserController;
import com.uca.tfg.dto.OrderDTO;
import com.uca.tfg.dto.UserDTO;
import com.uca.tfg.exception.EmailExistsException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.mapper.OrderMapper;
import com.uca.tfg.mapper.UserMapper;
import com.uca.tfg.model.User;
import com.uca.tfg.service.OrderService;
import com.uca.tfg.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	private MockMvc mvc;

	@Mock
	private UserService service;

	@Mock
	private UserMapper mapper;

	@Mock
	private OrderMapper orderMapper;

	@Mock
	private OrderService orderService;

	@InjectMocks
	private UserController controller;

	@Before
	public void setup() {
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	public void getAllUsers() throws Exception {
		mvc.perform(get("/users").contentType(APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().string(containsString("")));
	}

	@Test
	public void getUserById() throws Exception {
		User user = new User("user", "pass");
		given(service.getUser(anyLong())).willReturn(user);
		mvc.perform(get("/users/1").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getUserByIdNotFound() throws Exception {
		given(service.getUser(anyLong())).willReturn(null);
		mvc.perform(get("/users/1").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void getUserByEmail() throws Exception {
		User user = new User("user", "pass");
		user.setSurname("surname");
		given(service.getUserByEmail(anyString())).willReturn(user);
		mvc.perform(get("/users/email/a").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void getUserByEmailNotFound() throws Exception {
		given(service.getUserByEmail(anyString())).willReturn(null);
		mvc.perform(get("/users/email/a").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void getUsersByParam() throws Exception {
		mvc.perform(get("/users/search/a").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testRegisterUser() throws Exception {
		User user = new User("user", "pass");
		user.setName("name");
		user.setSurname("surname");
		String body = "{\n	\"email\":\"user\",\n	\"password\":\"pass\"\n}";
		given(service.registerNewUserAccount(any())).willReturn(user);
		given(mapper.mapDtoToEntity(any())).willReturn(user);
		mvc.perform(post("/users/signin").content(body).contentType(APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testRegisterUserException() throws Exception {
		User user = new User("user", "pass");
		user.setAddress("prueba");
		user.setName("name");
		user.setSurname("surname");
		String body = "{\n	\"email\":\"manuexcd@gmail.com\",\n	\"password\":\"pass\"\n}";
		given(service.registerNewUserAccount(any())).willThrow(EmailExistsException.class);
		mvc.perform(post("/users/signin").content(body).contentType(APPLICATION_JSON))
				.andExpect(status().is5xxServerError());
	}

	@Test
	public void testGetOrdersByUser() throws Exception {
		mvc.perform(get("/users/1/orders").contentType(APPLICATION_JSON)).andExpect(status().isOk());
	}

	@Test
	public void testGetOrdersByUserException() throws Exception {
		given(orderService.getOrdersByUser(anyLong(), any())).willThrow(UserNotFoundException.class);
		mvc.perform(get("/users/1/orders").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testCreateTemporalOrder() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		mvc.perform(
				post("/users/1/orders").content(obj.writeValueAsString(new OrderDTO())).contentType(APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testCreateTemporalOrderException() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		given(service.createTemporalOrder(anyLong(), any())).willThrow(UserNotFoundException.class);
		mvc.perform(
				post("/users/1/orders").content(obj.writeValueAsString(new OrderDTO())).contentType(APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testUpdateOrder() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		mvc.perform(
				put("/users/1/orders").content(obj.writeValueAsString(new OrderDTO())).contentType(APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testUpdateOrderException() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		given(service.updateOrder(anyLong(), any())).willThrow(UserNotFoundException.class);
		mvc.perform(
				put("/users/1/orders").content(obj.writeValueAsString(new OrderDTO())).contentType(APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void testUpdateTemporalOrder() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		mvc.perform(put("/users/1/orders/temporal").content(obj.writeValueAsString(new OrderDTO()))
				.contentType(APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testUpdateTemporalOrderException() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		given(service.updateTemporalOrder(anyLong(), any())).willThrow(UserNotFoundException.class);
		mvc.perform(put("/users/1/orders/temporal").content(obj.writeValueAsString(new OrderDTO()))
				.contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testCancelOrder() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		mvc.perform(put("/users/1/orders/cancel/1").content(obj.writeValueAsString(new OrderDTO()))
				.contentType(APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testCancelOrderException() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		given(service.cancelOrder(anyLong(), anyLong())).willThrow(UserNotFoundException.class);
		mvc.perform(put("/users/1/orders/cancel/1").content(obj.writeValueAsString(new OrderDTO()))
				.contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testConfirmUser() throws Exception {
		mvc.perform(post("/users/confirm/1").contentType(APPLICATION_JSON)).andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testConfirmUserException() throws Exception {
		given(service.confirmUser(anyLong())).willThrow(UserNotFoundException.class);
		mvc.perform(post("/users/confirm/1").contentType(APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void testUpdateUser() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		mvc.perform(put("/users/1").content(obj.writeValueAsString(new UserDTO())).contentType(APPLICATION_JSON))
				.andExpect(status().is2xxSuccessful());
	}

	@Test
	public void testUpdateUserException() throws Exception {
		ObjectMapper obj = new ObjectMapper();
		given(service.getUser(anyLong())).willThrow(UserNotFoundException.class);
		mvc.perform(put("/users/1").content(obj.writeValueAsString(new UserDTO())).contentType(APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
}
