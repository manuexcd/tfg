package com.uca.tfg.service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uca.tfg.exception.DuplicateUserException;
import com.uca.tfg.exception.EmailExistsException;
import com.uca.tfg.exception.UserNotFoundException;
import com.uca.tfg.model.Image;
import com.uca.tfg.model.Order;
import com.uca.tfg.model.User;
import com.uca.tfg.repository.ImageRepository;
import com.uca.tfg.repository.UserRepository;

@Service("userManager")
@DependsOn("imageManager")
public class UserManagerImp implements UserManager {

	@Autowired
	private UserRepository users;

	@Autowired
	private ImageRepository images;

	@PostConstruct
	public void init() {
		if (users.findAll().isEmpty()) {
			Optional<Image> image1 = images.findById((long) 1);
			Optional<Image> image2 = images.findById((long) 2);
			if (image1.isPresent())
				users.save(new User("Manuel", "Lara", "Plaza Algodonales 2", "638489260", "manuexcd@gmail.com",
						"password", image1.get()));
			if (image2.isPresent())
				users.save(new User("Antonia", "Ruiz", "Santiago Bernabeu", "638489261", "antoniaruiz@gmail.com",
						"password", image2.get()));
			if (image1.isPresent())
				users.save(new User("Luis", "Ramírez", "Plaza Algodonales 1", "638489262", "email1@gmail.com",
						"password", image1.get()));
			if (image2.isPresent())
				users.save(new User("María", "Gómez", "Santiago Bernabeu 2", "638489263", "email2@gmail.com",
						"password", image2.get()));
			if (image1.isPresent())
				users.save(new User("Antonio", "Martínez", "Plaza Algodonales 22", "638489264", "email3@gmail.com",
						"password", image1.get()));
			if (image2.isPresent())
				users.save(new User("Ana", "Bueno", "Santiago Bernabeu 22", "638489265", "email4@gmail.com", "password",
						image2.get()));
			if (image1.isPresent())
				users.save(new User("Manuel", "Gonzáles", "Plaza Algodonales 222", "638489266", "email5@gmail.com",
						"password", image1.get()));
			if (image2.isPresent())
				users.save(new User("Antonia", "Soto", "Santiago Bernabeu 222", "638489267", "email6@gmail.com",
						"password", image2.get()));
			if (image1.isPresent())
				users.save(new User("Gonzalo", "Fuentes", "Plaza Algodonales 2222", "638489268", "email7@gmail.com",
						"password", image1.get()));
			if (image2.isPresent())
				users.save(new User("Pepa", "Ruiz", "Santiago Bernabeu 21", "638489269", "email8@gmail.com", "password",
						image2.get()));
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

	public boolean emailExist(String email) {
		User user = users.findByEmail(email);
		return users.existsById(user.getId());
	}

	public Page<User> getAllUsers(Pageable pagination) {
		return users.findByOrderByName(pagination);
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

	public Page<User> getUsersByParam(String param, Pageable pagination) {
		if (param != null)
			return users.findByParam(param, pagination);
		return users.findAll(pagination);
	}

	public User addUser(User user) throws DuplicateUserException {
		if (!users.existsById(user.getId())) {
			return users.save(user);
		} else
			throw new DuplicateUserException();
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
		} else
			throw new UserNotFoundException();
	}

	public void deleteUser(long id) {
		users.deleteById(id);
	}
}
