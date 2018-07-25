package com.rest.phonebook.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import com.rest.phonebook.model.Contact;
import com.rest.phonebook.model.Phonebook;

import com.rest.phonebook.repository.PhonebookRepository;

@Service
public class PhonebookService {
	@Autowired
	PhonebookRepository phonebookRepository;

	public Phonebook findByphonebookname(String name) {
		return phonebookRepository.findByphonebookname(name);

	}
	
	public void createPhonebook(Phonebook phonebook ) {
		phonebookRepository.save(phonebook);
	}
	public List<Contact> listOfContactForPhonebook(@Param("id") long id) {
		return phonebookRepository.listOfContactForPhonebook(id);
	}

	public void updatePhonebookName(String phonebookName, long id , long userId) {
		phonebookRepository.updatePhonebookName(phonebookName, id , userId );
	}

	public List<Phonebook> listOfPhonebookForUser(long id) {
		return phonebookRepository.listOfPhonebookForUser(id);

	}

	public Phonebook getPhoneBookById( long id , long phonebookId) {
		 return phonebookRepository.getPhoneBookById(id, phonebookId);
	 }
	public void deletePhonebook(Long phonebookId, long useId) {
		phonebookRepository.deletePhonebookById(phonebookId, useId);
		
	
	}

}
