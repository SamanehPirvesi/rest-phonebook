package com.rest.phonebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.phonebook.model.Contact;
import com.rest.phonebook.repository.ContactRepository;

@Service
public class ContactService {
	@Autowired
	private ContactRepository contactRepository;
	
	public void createContact(Contact contact) {
		contactRepository.save(contact);
	}
	public Optional<Contact> getContactById(long id) {
		return contactRepository.findById(id);
	}
	public Contact getContactByName(String name) {
		return contactRepository.findByName(name);
	}

	public void deleteContact(Contact contact) {
		contactRepository.delete(contact);
	}
	public void deleteContactByName(String name) {
		 contactRepository.deleteByName(name);
	}
 	  public void updateContact(String contactName , String tellnumber ,long contactId , long phonebookId) {
 		 contactRepository.updateContact(contactName, tellnumber, contactId, phonebookId);
 	  }
 	 public Contact getContacById( long phonebookId , long contactId) {
 		return contactRepository.getContacById(phonebookId, contactId);
 	 }
 	public List<Contact> listOfContactForPhonebook(long phonebookId){
 		return contactRepository.listOfContactForPhonebook(phonebookId);
 	}
 	public void deleteContactById(long contactId , long phonebookId) {
 		contactRepository.deleteContactById(contactId, phonebookId);
 	}
    


}
