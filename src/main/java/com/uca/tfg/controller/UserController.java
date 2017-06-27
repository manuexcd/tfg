package com.uca.tfg.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.uca.tfg.dao.User;
import com.uca.tfg.service.UserManager;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserManager userManager;

	@RequestMapping(method = RequestMethod.GET)
	public Collection<User> getAllUsers() {
		return userManager.getAllUsers();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable long id) {
		return userManager.getUser(id);
	}

	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		return userManager.getUserByEmail(email);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User addUser(@RequestBody User user) {
		return userManager.addUser(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable long id) {
		return userManager.deleteUser(id);
	}
}
