package com.uca.tfg.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.uca.tfg.dao.ImageDAO;
import com.uca.tfg.dao.UserDAO;
import com.uca.tfg.exceptions.DuplicateUserException;
import com.uca.tfg.exceptions.EmailExistsException;
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
			users.save(new User("Manuel", "Lara", "Plaza Algodonales 2 3ºD", "+34 638489260", "manuexcd@gmail.com",
					null, images.findById((long) 6).orElse(null), "pass", "ROLE_ADMIN"));
			users.save(new User("Cristiano", "Ronaldo", "Estadio Santiago Bernabéu", "+34 000000002", "CR7@gmail.com",
					null, images.findById((long) 4).orElse(null), "pass", "ROLE_USER"));
			users.save(new User("Lionel", "Messi", "Estadio Nou Camp", "+34 000000003", "leomessi@gmail.com", null,
					images.findById((long) 5).orElse(null), "pass", "ROLE_USER"));
		}
	}

	@Transactional
	@Override
	public User registerNewUserAccount(User user) throws EmailExistsException {

		if (emailExist(user.getEmail())) {
			throw new EmailExistsException("There is an account with that email adress: " + user.getEmail());
		}

		return users.save(user);
	}

	private boolean emailExist(String email) {
		User user = users.findByEmail(email);
		if (user != null)
			return true;
		else
			return false;
	}

	public Collection<User> getAllUsers() {
		return users.findByOrderByName();
	}

	public Collection<Order> getOrders(long id) throws UserNotFoundException {
		if (users.findById(id).isPresent())
			return users.findById(id).orElse(null).getOrders();
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
		User user = null;

		if (users.findById(id).isPresent()) {
			user = users.findById(id).orElse(null);
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
