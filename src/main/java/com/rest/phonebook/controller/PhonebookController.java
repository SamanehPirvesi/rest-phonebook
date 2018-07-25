package com.rest.phonebook.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.rest.phonebook.model.Phonebook;
import com.rest.phonebook.model.User;
import com.rest.phonebook.service.PhonebookService;
import com.rest.phonebook.service.UserService;

@RestController
@RequestMapping("/users/{userId}/phonebooks")
public class PhonebookController {
	private PhonebookService phonebookService;
	private UserService userService;

	@Autowired // writing it explicitely is optional
	public PhonebookController(PhonebookService phonebookService, UserService userService) {
		this.phonebookService = phonebookService;
		this.userService = userService;
	}

	@GetMapping("/{phonebookId}")
	public ResponseEntity<Phonebook> getPhonebookById(@PathVariable("userId") long userId,
			@PathVariable("phonebookId") long phonebookId) {
		Phonebook phonebook = phonebookService.getPhoneBookById(userId, phonebookId);
		if (phonebook == null) {
			throw new UserNotFoundException("user or phonebook does not exist ");
		}
		return new ResponseEntity<Phonebook>(phonebook, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Phonebook>> getPhonebooksOfUser(@PathVariable("userId") long userId) {
		User user = userService.getUserById(userId).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("user does not exist ");
		}
		return new ResponseEntity<List<Phonebook>>(phonebookService.listOfPhonebookForUser(userId), HttpStatus.OK);
	}

	@PostMapping()
	public void addPhonebook(@PathVariable("userId") long userId, @RequestBody Phonebook phonebook) {
		User user = userService.getUserById(userId).orElse(null);
		if (user == null) {
			throw new UserNotFoundException("user does not exist ");
		}
		phonebook.setUser(user);
		user.addphonebook(phonebook);
		phonebookService.createPhonebook(phonebook);
	}

	@PutMapping("/{phonebookId}")
	public void updatePhonebook(@PathVariable("userId") long userId, @PathVariable("phonebookId") long phonebookId,
			@RequestBody Phonebook ph) {
		Phonebook phonebook = phonebookService.getPhoneBookById(userId, phonebookId);
		if (phonebook == null) {
			throw new UserNotFoundException("user or phonebook does not exist ");
		}
		phonebookService.updatePhonebookName(ph.getPhonebookname(), phonebookId, userId);
	}

	@DeleteMapping("/{phonebookId}")
	public void deletePhonebook(@PathVariable("phonebookId") long phonebookId, @PathVariable("userId") long userId) {
		Phonebook phonebook = phonebookService.getPhoneBookById(userId, phonebookId);
		if (phonebook == null) {
			throw new UserNotFoundException("user or phonebook does not exist ");
		}
		phonebookService.deletePhonebook(phonebookId, userId);
	}

}
