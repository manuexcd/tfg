package com.uca.tfg.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.UserDAO;
import com.uca.tfg.exception.DuplicateUserException;
import com.uca.tfg.exception.EmailExistsException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;

@Service("userManager")
@DependsOn("imageManager")
public class UserManagerImp implements UserManager {

	@Autowired
	private UserDAO users;

	@Transactional
	@Override
	public User registerNewUserAccount(User user) throws EmailExistsException {

		if (emailExist(user.getEmail())) {
			throw new EmailExistsException("There is an account with that email adress: " + user.getEmail());
		}

		return users.save(user);
	}

	public boolean emailExist(String email) {
		User user = users.findByEmail(email);
		return users.existsById(user.getId());
	}

	public Collection<User> getAllUsers() {
		return users.findByOrderByName();
	}

	public Collection<Order> getOrders(long id) throws UserNotFoundException {
		Optional<User> optional = users.findById(id); 
		if (optional.isPresent())
			return optional.get().getOrders();
		else
			throw new UserNotFoundException();
	}

	public User getUser(long id) {
		return users.findById(id).orElse(null);
	}

	public User getUserByEmail(String email) {
		return users.findByEmail(email);
	}

	public List<User> getUsersByParam(String param) {
		if (param != null)
			return users.findByParam(param);
		return users.findAll();
	}

	public User addUser(User user) throws DuplicateUserException {
		if (!users.existsById(user.getId())) {
			return users.save(user);
		} else
			throw new DuplicateUserException();
	}

	public ResponseEntity<Order> addOrder(long id) throws UserNotFoundException {
		Optional<User> optional = users.findById(id);
		User user = null;

		if (optional.isPresent()) {
			user = optional.get();
			Order order = new Order(new Timestamp(System.currentTimeMillis()), user);
			user.getOrders().add(order);
			users.save(user);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} else
			throw new UserNotFoundException();
	}

	public User deleteUser(long id) {
		if (users.findById(id).isPresent()) {
			User user = users.findById(id).orElse(null);
			users.delete(user);
			return user;
		} else
			return null;
	}
}
