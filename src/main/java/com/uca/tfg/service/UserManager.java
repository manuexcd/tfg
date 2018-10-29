package com.uca.tfg.service;

import java.util.Collection;
import java.util.List;

import com.uca.tfg.exception.DuplicateUserException;
import com.uca.tfg.exception.EmailExistsException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;

public interface UserManager {
	public User registerNewUserAccount(User user) throws EmailExistsException;

	public Collection<User> getAllUsers(int page, int pageSize);

	public Collection<Order> getOrders(long id) throws UserNotFoundException;

	public User getUser(long id);

	public User getUserByEmail(String email);

	public List<User> getUsersByParam(String param);

	public User addUser(User user) throws DuplicateUserException;

	public Order addOrder(long id) throws UserNotFoundException;

	public void deleteUser(long id);
	
	public boolean emailExist(String email);
}
