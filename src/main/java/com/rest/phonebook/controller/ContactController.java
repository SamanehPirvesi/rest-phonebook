package com.rest.phonebook.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.phonebook.model.Contact;
import com.rest.phonebook.model.Phonebook;
import com.rest.phonebook.service.ContactService;
import com.rest.phonebook.service.PhonebookService;

@RestController
@RequestMapping("/users/{userId}/phonebooks/{phonebookId}/contacts")
public class ContactController {

	private PhonebookService phonebookService;
	private ContactService contactService;

	public ContactController(PhonebookService phonebookService ,ContactService contactService ) {
		this.phonebookService = phonebookService;
		this.contactService = contactService;
		}
	@GetMapping("/{contactId}")
	public Contact getPhonebookById(@PathVariable("phonebookId") long phonebookId , @PathVariable ("contactId") long contactId) {
	return contactService.getContacById(phonebookId,contactId);
	}
	
	@GetMapping()
	public List<Contact> getContactsForPhonebook(@PathVariable("phonebookId")long phonebookId ) {
	return contactService.listOfContactForPhonebook(phonebookId);
	}
	@PostMapping()
	 public void addContact(@PathVariable("userId") long userId , @PathVariable("phonebookId") long phonebookId , @RequestBody Contact contact) {
			Phonebook phonebook=phonebookService.getPhoneBookById(userId, phonebookId);
			phonebook.addContact(contact);
			contact.setPhonebook(phonebook);
			contactService.createContact(contact);
	 }
	@PutMapping("/{contactId}")
	public void updatePhonebook(@PathVariable("phonebookId") long phonebookId , @PathVariable ("contactId") long contactId,@RequestBody Contact contact) {
		contactService.updateContact(contact.getName(),contact.getTellNumber(),contactId,phonebookId);
				
	}
	@DeleteMapping("/{contactId}")
	public void deletePhonebook(@PathVariable("phonebookId") long phonebookId , @PathVariable ("contactId") long contactId) {
		contactService.deleteContactById(contactId, phonebookId);	
	}

}
