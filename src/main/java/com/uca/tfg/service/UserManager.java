package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

import com.uca.tfg.dao.User;

public interface UserManager {
	public Collection<User> getAllUsers();

	public ResponseEntity<User> getUser(long id);

	public ResponseEntity<User> getUserByEmail(String email);

	public User addUser(User user);

	public ResponseEntity<User> deleteUser(long id);
	
}
