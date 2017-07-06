package com.uca.tfg.service;

import java.util.Collection;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.UserDAO;
import com.uca.tfg.exceptions.DuplicateUserException;
import com.uca.tfg.exceptions.UserNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;

@Service("userManager")
public class UserManagerImp implements UserManager {

	@Autowired
	private UserDAO users;

	@PostConstruct
	public void init() {
		if (users.findAll().isEmpty()) {
			users.save(new User("User", "One", "AddressUserOne", "+34 000000001", "userOne@gmail.com", null));
			users.save(new User("User", "Two", "AddressUserTwo", "+34 000000002", "userTwo@gmail.com", null));
			users.save(new User("User", "Three", "AddressUserThree", "+34 000000003", "userThree@gmail.com", null));
		}
	}

	public Collection<User> getAllUsers() {
		return users.findAll();
	}

	public Collection<Order> getOrders(long id) throws UserNotFoundException {
		User user = users.findOne(id);

		if (user != null)
			return user.getOrders();
		else
			throw new UserNotFoundException();
	}

	public ResponseEntity<User> getUser(long id) {
		User user = users.findOne(id);

		if (user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<User> getUserByEmail(String email) {
		User user = users.findByEmail(email);

		if (user != null)
			return new ResponseEntity<>(user, HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public User addUser(User user) throws DuplicateUserException {
		if (!users.exists(user.getId())) {
			return users.save(user);
		} else
			throw new DuplicateUserException();
	}

	public ResponseEntity<Order> addOrder(long id) throws UserNotFoundException {
		User user = users.findOne(id);
		Order order = new Order(new Date(), user);

		if (user != null) {
			user.getOrders().add(order);
			users.save(user);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} else
			throw new UserNotFoundException();
	}

	public ResponseEntity<User> deleteUser(long id) {
		User user = users.findOne(id);

		if (user != null) {
			users.delete(id);
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
