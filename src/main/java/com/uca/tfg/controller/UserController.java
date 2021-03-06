package com.uca.tfg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.uca.tfg.dto.UserDTO;
import com.uca.tfg.exception.DuplicateUserException;
import com.uca.tfg.exception.EmailExistsException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.mapper.UserMapper;
import com.uca.tfg.model.Constants;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;
import com.uca.tfg.service.UserManager;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private UserMapper mapper;

	@GetMapping
	public ResponseEntity<Page<User>> getAllUsers(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(userManager.getAllUsers(PageRequest.of(page, pageSize)), HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<User> getUser(@PathVariable long id) throws UserNotFoundException {
		User user = userManager.getUser(id);
		if (user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		User user = userManager.getUserByEmail(email);
		if (user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/search/{param}")
	public ResponseEntity<Page<User>> getUsersByParam(@PathVariable String param,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(userManager.getUsersByParam(param, PageRequest.of(page, pageSize)), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<User> addUser(@RequestBody UserDTO dto) throws DuplicateUserException {
		return new ResponseEntity<>(userManager.addUser(mapper.mapDtoToEntity(dto)), HttpStatus.CREATED);
	}

	@PostMapping(value = "/registration")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> registerUserAccount(@RequestBody UserDTO dto, WebRequest request) {
		User registered = null;
		try {
			registered = userManager.registerNewUserAccount(mapper.mapDtoToEntity(dto));
		} catch (EmailExistsException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(registered, HttpStatus.CREATED);
	}

	@PostMapping(value = "/{id}")
	public ResponseEntity<Order> addOrder(@PathVariable long id) throws UserNotFoundException {
		return new ResponseEntity<>(userManager.addOrder(id), HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable long id) throws UserNotFoundException {
		User user = userManager.getUser(id);
		if (user != null) {
			userManager.deleteUser(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
