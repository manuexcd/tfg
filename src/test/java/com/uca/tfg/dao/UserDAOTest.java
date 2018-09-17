package com.uca.tfg.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.uca.tfg.model.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserDAOTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserDAO users;

	@Before
	public void setUp() {
		String string = "Prueba";
		User user = new User(string, string, string, string, string, null, null, string, "ROLE_USER");
		entityManager.persist(user);
		entityManager.flush();
	}

	@Test
	public void findByEmail() {
		User found = users.findByEmail("Prueba");
		assertTrue(found.getEmail().equals("Prueba"));
	}

	@Test
	public void findAll() {
		assertNotNull(users.findAll());
	}
	
	@Test
	public void findById() {
		assertNotNull(users.findById((long) 1));
	}
	
	@Test
	public void findByParam() {
		assertNotNull(users.findByParam("Prueba"));
	}
	
	@Test
	public void findByOrderByName() {
		assertNotNull(users.findByOrderByName());
	}
}