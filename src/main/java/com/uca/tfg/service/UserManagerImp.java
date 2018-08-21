package com.uca.tfg.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.ImageDAO;
import com.uca.tfg.dao.UserDAO;
import com.uca.tfg.exceptions.DuplicateUserException;
import com.uca.tfg.exceptions.UserNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;

@Service("userManager")
@DependsOn("imageManager")
public class UserManagerImp implements UserManager {

	@Autowired
	private UserDAO users;
	
	@Autowired
	private ImageDAO images;

	@PostConstruct
	public void init() {
		if (users.findAll().isEmpty()) {
			System.out.println("PostConstruct USERS");
			users.save(new User("Manuel", "Lara", "Plaza Algodonales 2 3ºD", "+34 638489260", "manuexcd@gmail.com", null, images.findById((long) 6).get()));
			users.save(new User("Cristiano", "Ronaldo", "Estadio Santiago Bernabéu", "+34 000000002", "CR7@gmail.com", null, images.findById((long) 4).get()));
			users.save(new User("Lionel", "Messi", "Estadio Nou Camp", "+34 000000003", "leomessi@gmail.com", null, images.findById((long) 5).get()));
		}
	}

	public Collection<User> getAllUsers() {
		return users.findByOrderByName();
	}

	public Collection<Order> getOrders(long id) throws UserNotFoundException {
		User user = users.findById(id).get();

		if (user != null)
			return user.getOrders();
		else
			throw new UserNotFoundException();
	}

	public User getUser(long id) {
		User user = users.findById(id).get();

		if (user != null)
			return user;
		else
			return null;
	}

	public User getUserByEmail(String email) {
		User user = users.findByEmail(email);

		if (user != null)
			return user;
		else
			return null;
	}
	
	public List<User> getUsersByParam(String param) {
		if(param != null)
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
		User user = users.findById(id).get();
		Order order = new Order(new Date(), user);

		if (user != null) {
			user.getOrders().add(order);
			users.save(user);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} else
			throw new UserNotFoundException();
	}

	public User deleteUser(long id) {
		User user = users.findById(id).get();

		if (user != null) {
			users.delete(user);
			return user;
		} else
			return null;
	}
}
