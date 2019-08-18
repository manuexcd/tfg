package com.uca.tfg.service;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uca.tfg.exception.EmailExistsException;
import com.uca.tfg.exception.OrderNotFoundException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;

public interface UserService {
	public User registerNewUserAccount(User user) throws EmailExistsException;

	public Page<User> getAllUsers(Pageable pagination);

	public Collection<Order> getOrders(long id) throws UserNotFoundException;

	public User getUser(long id) throws UserNotFoundException;

	public User getUserByEmail(String email);

	public Page<User> getUsersByParam(String param, Pageable pagination);

	public Order createTemporalOrder(long id, Order order) throws UserNotFoundException;

	public Order updateOrder(long id, Order order) throws UserNotFoundException;

	public Order updateTemporalOrder(long id, Order order) throws UserNotFoundException, OrderNotFoundException;

	public Order cancelOrder(long id, long orderId) throws UserNotFoundException, OrderNotFoundException;

	public void deleteUser(long id);

	public boolean emailExist(String email);

	public User confirmUser(long id) throws UserNotFoundException;
}
