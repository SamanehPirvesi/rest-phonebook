package com.rest.phonebook.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rest.phonebook.exception.UserNotFoundException;
import com.rest.phonebook.model.User;
import com.rest.phonebook.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") int id) {
		User u = userService.getUserById(id).orElse(null);
		if (u == null) {
			throw new UserNotFoundException("User does not exist ");
		}
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
	}

	@PostMapping()
	public void addUser(@RequestBody User u) {
		userService.createUser(u);
	}

	@PutMapping("/{userId}")
	public void updateUser(@RequestBody User u, @PathVariable("userId") long userId) {
		User user = userService.getUserById(userId).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("User does not exist ");
		}
		userService.updateUserName(u.getUsername(), userId);
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") long userId) {
		User user = userService.getUserById(userId).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("User does not exist ");
		}
		userService.deleteById(userId);
	}

}
