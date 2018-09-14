package com.uca.tfg.service;

import java.util.Collection;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.uca.tfg.exception.DuplicateUserException;
import com.uca.tfg.exception.EmailExistsException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;

public interface UserManager {
	public User registerNewUserAccount(User user) throws EmailExistsException;

	public Collection<User> getAllUsers();

	public Collection<Order> getOrders(long id) throws UserNotFoundException;

	public User getUser(long id);

	public User getUserByEmail(String email);

	public List<User> getUsersByParam(String param);

	public User addUser(User user) throws DuplicateUserException;

	public ResponseEntity<Order> addOrder(long id) throws UserNotFoundException;

	public User deleteUser(long id);
	
	public boolean emailExist(String email);
}
