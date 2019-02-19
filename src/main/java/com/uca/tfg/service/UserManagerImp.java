package com.uca.tfg.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uca.tfg.exception.DuplicateUserException;
import com.uca.tfg.exception.EmailExistsException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;
import com.uca.tfg.repository.UserRepository;

@Service("userManager")
public class UserManagerImp implements UserManager {

	@Autowired
	private UserRepository users;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	@Override
	public User registerNewUserAccount(User user) throws EmailExistsException {

		if (emailExist(user.getEmail())) {
			throw new EmailExistsException("There is an account with that email adress: " + user.getEmail());
		}

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return users.save(user);
	}

	public boolean emailExist(String email) {
		User user = users.findByEmail(email);
		if (user != null)
			return users.existsById(user.getId());
		else
			return false;
	}

	public Page<User> getAllUsers(Pageable pagination) {
		return users.findByOrderByName(pagination);
	}

	public Collection<Order> getOrders(long id) throws UserNotFoundException {
		return users.findById(id).map(User::getOrders).orElseThrow(UserNotFoundException::new);
	}

	public User getUser(long id) throws UserNotFoundException {
		return users.findById(id).orElseThrow(UserNotFoundException::new);
	}

	public User getUserByEmail(String email) {
		return users.findByEmail(email);
	}

	public Page<User> getUsersByParam(String param, Pageable pagination) {
		return Optional.ofNullable(param).map(parameter -> users.findByParam(parameter, pagination))
				.orElse(users.findAll(pagination));
	}

	public User addUser(User user) throws DuplicateUserException {
		return Optional.ofNullable(user).filter(newUser -> users.existsById(newUser.getId()))
				.map(newUser -> users.save(newUser)).orElseThrow(DuplicateUserException::new);
	}

	public Order addOrder(long id) throws UserNotFoundException {
		Optional<User> optional = users.findById(id);
		User user = null;

		if (optional.isPresent()) {
			user = optional.get();
			Order order = new Order(new Timestamp(System.currentTimeMillis()), user);
			user.getOrders().add(order);
			users.save(user);
			return order;
		} else {
			throw new UserNotFoundException();
		}

//		return users.findById(id)
//				.map(user -> user.getOrders().add(new Order(new Timestamp(System.currentTimeMillis()), user)));
	}

	public void deleteUser(long id) {
		users.deleteById(id);
	}
}
