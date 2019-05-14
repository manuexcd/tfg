package com.uca.tfg.controller;

import java.util.Optional;

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

import com.uca.tfg.dto.OrderDTO;
import com.uca.tfg.dto.UserDTO;
import com.uca.tfg.exception.DuplicateUserException;
import com.uca.tfg.exception.EmailExistsException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.mapper.OrderMapper;
import com.uca.tfg.mapper.UserMapper;
import com.uca.tfg.model.Constants;
import com.uca.tfg.model.User;
import com.uca.tfg.service.UserManager;

@RestController
@RequestMapping(value = Constants.PATH_USERS)
public class UserController {

	@Autowired
	private UserManager userManager;

	@Autowired
	private UserMapper mapper;

	@Autowired
	private OrderMapper orderMapper;

	@GetMapping
	public ResponseEntity<Page<UserDTO>> getAllUsers(
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(
				mapper.mapEntityPageToDtoPage(userManager.getAllUsers(PageRequest.of(page, pageSize))), HttpStatus.OK);
	}

	@GetMapping(value = Constants.PARAM_ID)
	public ResponseEntity<UserDTO> getUser(@PathVariable long id) throws UserNotFoundException {
		return Optional.ofNullable(userManager.getUser(id))
				.map(user -> new ResponseEntity<>(mapper.mapEntityToDto(user), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping(value = Constants.PATH_EMAIL + Constants.PARAM_EMAIL)
	public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
		return Optional.ofNullable(userManager.getUserByEmail(email))
				.map(user -> new ResponseEntity<>(mapper.mapEntityToDto(user), HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping(value = Constants.PATH_SEARCH + Constants.PARAM)
	public ResponseEntity<Page<UserDTO>> getUsersByParam(@PathVariable String param,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_PAGE) int page,
			@RequestParam(defaultValue = Constants.PAGINATION_DEFAULT_SIZE) int pageSize) {
		return new ResponseEntity<>(
				mapper.mapEntityPageToDtoPage(userManager.getUsersByParam(param, PageRequest.of(page, pageSize))),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO dto) throws DuplicateUserException {
		return new ResponseEntity<>(mapper.mapEntityToDto(userManager.addUser(mapper.mapDtoToEntity(dto))),
				HttpStatus.CREATED);
	}

	@PostMapping(value = Constants.PATH_SIGN_IN)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UserDTO> registerUserAccount(@RequestBody UserDTO dto, WebRequest request) {
		User registered = null;
		try {
			registered = userManager.registerNewUserAccount(mapper.mapDtoToEntity(dto));
		} catch (EmailExistsException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(mapper.mapEntityToDto(registered), HttpStatus.CREATED);
	}

	@PostMapping(value = Constants.PARAM_ID)
	public ResponseEntity<OrderDTO> addOrder(@PathVariable long id) throws UserNotFoundException {
		return new ResponseEntity<>(orderMapper.mapEntityToDto(userManager.addOrder(id)), HttpStatus.CREATED);
	}

	@DeleteMapping(value = Constants.PARAM_ID)
	public ResponseEntity<UserDTO> deleteUser(@PathVariable long id) {
		userManager.deleteUser(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
