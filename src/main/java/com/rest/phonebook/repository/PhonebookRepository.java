package com.rest.phonebook.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rest.phonebook.model.Contact;
import com.rest.phonebook.model.Phonebook;

public interface PhonebookRepository extends CrudRepository<Phonebook, Long> {

	public Phonebook findByphonebookname(String name);

	// @Modifying
	// @Transactional
	// @Query("delete from Phonebook p where p.Phonebook = ?1")
	// public void deleteByName(String name);
	@Query("select c from Contact c where phonebook_id=id")
	public List<Contact> listOfContactForPhonebook(@Param("id") long id);

	@Modifying
	@Transactional
	@Query("update Phonebook p set p.phonebookname= ?1  where p.id= ?2 and user_userId=?3")
	public void updatePhonebookName(String phonebookName, long id , long userId);

	@Query("select p from Phonebook p where user_userId=?1")
	public List<Phonebook> listOfPhonebookForUser(long id);
	
	@Query("select p from Phonebook p where user_userId=?1 and id=?2 ")
	public Phonebook getPhoneBookById( long id , long phonebookId);

	@Modifying
    @Transactional
    @Query("delete from Phonebook p where p.id = ?1 and user_userId=?2")
   public void deletePhonebookById(long phonebookid , long userid);
}
