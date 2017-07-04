package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.uca.tfg.dao.Order;
import com.uca.tfg.dao.User;
import com.uca.tfg.exceptions.DuplicateUserException;
import com.uca.tfg.exceptions.UserNotFoundException;

public interface UserManager {
	public Collection<User> getAllUsers();
	
	public Collection<Order> getOrders(long id) throws UserNotFoundException;

	public ResponseEntity<User> getUser(long id);

	public ResponseEntity<User> getUserByEmail(String email);

	public User addUser(User user) throws DuplicateUserException;
	
	public ResponseEntity<Order> addOrder(long id) throws UserNotFoundException;

	public ResponseEntity<User> deleteUser(long id);

}
