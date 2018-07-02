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

import com.uca.tfg.exceptions.DuplicateUserException;
import com.uca.tfg.exceptions.UserNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;
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
		User user = userManager.getUser(id);
		if(user != null)
			return new ResponseEntity<User>(user, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		User user = userManager.getUserByEmail(email);
		if(user != null)
			return new ResponseEntity<User>(user, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/search/{param}", method = RequestMethod.GET)
	public Collection<User> getUsersByParam(@PathVariable String param) {
		return userManager.getUsersByParam(param);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User addUser(@RequestBody User user) throws DuplicateUserException {
		return userManager.addUser(user);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Order> addOrder(@PathVariable long id) throws UserNotFoundException {
		return userManager.addOrder(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable long id) {
		User user = userManager.getUser(id);
		if(user != null)
			return new ResponseEntity<User>(user, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
