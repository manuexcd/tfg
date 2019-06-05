package com.uca.tfg.service;

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
import com.uca.tfg.model.Constants;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;
import com.uca.tfg.repository.UserRepository;

@Service("userManager")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private OrderService orderService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional
	@Override
	public User registerNewUserAccount(User user) throws EmailExistsException {

		if (emailExist(user.getEmail())) {
			throw new EmailExistsException("There is an account with that email adress: " + user.getEmail());
		}

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	public boolean emailExist(String email) {
		User user = repository.findByEmail(email);
		if (user != null)
			return repository.existsById(user.getId());
		else
			return false;
	}

	@Override
	public Page<User> getAllUsers(Pageable pagination) {
		return repository.findByOrderByName(pagination);
	}

	@Override
	public Collection<Order> getOrders(long id) throws UserNotFoundException {
		return repository.findById(id).map(User::getOrders).orElseThrow(UserNotFoundException::new);
	}

	@Override
	public Order createTemporalOrder(long id, Order order) throws UserNotFoundException {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			order.setOrderStatus(Constants.ORDER_STATUS_TEMPORAL);
			order.setUser(user.get());
			return orderService.createTemporalOrder(order);
		} else
			throw new UserNotFoundException();
	}
	
	@Override
	public Order updateOrder(long id, Order order) throws UserNotFoundException {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			order.setUser(user.get());
			return orderService.updateOrder(order);
		} else
			throw new UserNotFoundException();
	}
	
	@Override
	public Order updateTemporalOrder(long id, Order order) throws UserNotFoundException {
		Optional<User> user = repository.findById(id);
		if (user.isPresent()) {
			order.setUser(user.get());
			return orderService.confirmTemporalOrder(order);
		} else
			throw new UserNotFoundException();
	}

	@Override
	public User getUser(long id) throws UserNotFoundException {
		return repository.findById(id).orElseThrow(UserNotFoundException::new);
	}

	@Override
	public User getUserByEmail(String email) {
		return repository.findByEmail(email);
	}

	@Override
	public Page<User> getUsersByParam(String param, Pageable pagination) {
		return Optional.ofNullable(param).map(parameter -> repository.findByParam(parameter, pagination))
				.orElse(repository.findAll(pagination));
	}

	@Override
	public User addUser(User user) throws DuplicateUserException {
		return Optional.ofNullable(user).filter(newUser -> repository.existsById(newUser.getId()))
				.map(newUser -> repository.save(newUser)).orElseThrow(DuplicateUserException::new);
	}

	@Override
	public void deleteUser(long id) {
		repository.deleteById(id);
	}
}