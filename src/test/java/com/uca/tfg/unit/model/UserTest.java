package com.uca.tfg.unit.model;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.uca.tfg.model.Image;
import com.uca.tfg.model.User;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
	
	@Test
	public void constructor() {
		User user = new User("username", "password");
		assertNotNull(user);
	}
	
	@Test
	public void constructor2() {
		User user = new User("nombre", "apellidos", "direccion", "telefono", "email", "contraseña", new Image());
		assertNotNull(user);
	}

	@Test
	public void getFullName() {
		String string = "Prueba";
		User user = new User(string, string, string, string, string, string);
		assertNotNull(user.getFullName());
	}
}
