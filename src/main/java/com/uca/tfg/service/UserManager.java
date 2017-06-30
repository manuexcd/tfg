package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.uca.tfg.dao.Order;
import com.uca.tfg.dao.User;

public interface UserManager {
	public Collection<User> getAllUsers();
	
	public Collection<Order> getOrders(long id);

	public ResponseEntity<User> getUser(long id);

	public ResponseEntity<User> getUserByEmail(String email);

	public User addUser(User user);
	
	public ResponseEntity<Order> addOrder(long id);

	public ResponseEntity<User> deleteUser(long id);

}
