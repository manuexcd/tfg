package com.uca.tfg.service;

import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.uca.tfg.exceptions.DuplicateUserException;
import com.uca.tfg.exceptions.UserNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;

public interface UserManager {
	public Collection<User> getAllUsers();
	
	public Collection<Order> getOrders(long id) throws UserNotFoundException;

	public User getUser(long id);

	public User getUserByEmail(String email);
	
	public List<User> getUsersByParam(String param);

	public User addUser(User user) throws DuplicateUserException;
	
	public ResponseEntity<Order> addOrder(long id) throws UserNotFoundException;

	public User deleteUser(long id);

}
