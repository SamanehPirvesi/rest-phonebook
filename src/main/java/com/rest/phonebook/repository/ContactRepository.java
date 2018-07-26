package com.rest.phonebook.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rest.phonebook.model.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

	public Contact findByName(String name);

	@Modifying
	@Transactional
	@Query("delete from Contact c where c.name = ?1")
	public void deleteByName(String name);

	@Modifying
	@Transactional
	@Query("delete from Contact c where c.contact_id = ?1 and phonebook_id=?2")
	public void deleteContactById(long contactId, long phonebookId);

	@Modifying
	@Transactional
	@Query("update Contact c set c.name= ?1 , c.tellNumber=?2  where c.contact_id= ?3 and phonebook_id=?4")
	public void updateContact(String contactName, String tellnumber, long contactId, long phonebookId);

	@Query("select c from Contact c where phonebook_id=?1 and contact_id=?2 ")
	public Contact getContacById(long phonebookId, long contactId);

	@Query("select p from Contact p where phonebook_id=?1")
	public List<Contact> listOfContactForPhonebook(long id);
}
