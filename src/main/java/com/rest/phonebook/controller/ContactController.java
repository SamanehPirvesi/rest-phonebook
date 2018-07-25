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
import com.rest.phonebook.model.Contact;
import com.rest.phonebook.model.Phonebook;
import com.rest.phonebook.service.ContactService;
import com.rest.phonebook.service.PhonebookService;

@RestController
@RequestMapping("/users/{userId}/phonebooks/{phonebookId}/contacts")
public class ContactController {

	private PhonebookService phonebookService;
	private ContactService contactService;

	public ContactController(PhonebookService phonebookService, ContactService contactService) {
		this.phonebookService = phonebookService;
		this.contactService = contactService;
	}

	@GetMapping("/{contactId}")
	public ResponseEntity<Contact> getPhonebookById(@PathVariable("userId") long userId,
			@PathVariable("phonebookId") long phonebookId, @PathVariable("contactId") long contactId) {
		Phonebook phonebook = phonebookService.getPhoneBookById(userId, phonebookId);
		Contact contact = contactService.getContacById(phonebookId, contactId);
		if (phonebook == null) {
			throw new UserNotFoundException("user or phonebook does not exist ");
		} else if (contact == null) {
			throw new UserNotFoundException("contact does not exist ");
		}
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);
	}

	@GetMapping()
	public ResponseEntity<List<Contact>> getContactsForPhonebook(@PathVariable("userId") long userId,@PathVariable("phonebookId") long phonebookId) {
		Phonebook phonebook = phonebookService.getPhoneBookById(userId, phonebookId);
		if (phonebook == null) {
			throw new UserNotFoundException(" User or phonebook does not exist ");
		}
		return new ResponseEntity<List<Contact>>(contactService.listOfContactForPhonebook(phonebookId), HttpStatus.OK);
	}

	@PostMapping()
	public void addContact(@PathVariable("userId") long userId, @PathVariable("phonebookId") long phonebookId,@RequestBody Contact contact) {
		Phonebook phonebook = phonebookService.getPhoneBookById(userId, phonebookId);
		if (phonebook == null) {
			throw new UserNotFoundException("user or phonebook does not exist ");
		}
		phonebook.addContact(contact);
		contact.setPhonebook(phonebook);
		contactService.createContact(contact);
	}

	@PutMapping("/{contactId}")
	public void updatePhonebook(@PathVariable("userId") long userId, @PathVariable("phonebookId") long phonebookId,	@PathVariable("contactId") long contactId, @RequestBody Contact contact) {
		Phonebook phonebook = phonebookService.getPhoneBookById(userId, phonebookId);
		Contact readContact = contactService.getContacById(phonebookId, contactId);
		if (phonebook == null) {
			throw new UserNotFoundException("user or phonebook does not exist ");
		}
	 else if (readContact == null) {
		throw new UserNotFoundException("contact does not exist ");
	}
		contactService.updateContact(contact.getName(), contact.getTellNumber(), contactId, phonebookId);
	}

	@DeleteMapping("/{contactId}")
	public void deletePhonebook(@PathVariable("userId") long userId, @PathVariable("phonebookId") long phonebookId,	@PathVariable("contactId") long contactId) {
		Phonebook phonebook = phonebookService.getPhoneBookById(userId, phonebookId);
		Contact contact = contactService.getContacById(phonebookId, contactId);
		if (phonebook == null) {
			throw new UserNotFoundException("user or phonebook does not exist ");
		} else if (contact == null) {
			throw new UserNotFoundException("contact does not exist ");
		}
		contactService.deleteContactById(contactId, phonebookId);
	}

}
