package com.rest.phonebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.rest.phonebook.model.Phonebook;
import com.rest.phonebook.model.User;
import com.rest.phonebook.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void createUser(User user) {
		userRepository.save(user);
	}

	public Optional<User> getUserById(long id) {
		return userRepository.findById(id);
	}

	public User getUserByUserName(String username) {
		return userRepository.findByUsername(username);
	}

	public List<User> getAllUser() {
		return (List<User>) userRepository.findAll();
	}

	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	public List<Phonebook> listOfPhonebookForUser(@Param("id") long id) {
		return (List<Phonebook>) userRepository.listOfPhonebookForUser(id);
	}

	public void updateUserName(String userName, long userId) {
		userRepository.updateUserName(userName, userId);
	}

	public void deleteById(long id) {
		userRepository.deleteById(id);
	}
}
