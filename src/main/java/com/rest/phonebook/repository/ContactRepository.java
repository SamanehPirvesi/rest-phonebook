package com.rest.phonebook.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.rest.phonebook.model.Contact;


@Repository
public interface ContactRepository extends CrudRepository<Contact,Long> {
	
    public Contact findByName(String name);

    @Modifying
    @Transactional
    @Query("delete from Contact c where c.name = ?1")
   public void deleteByName(String name);
    
    @Modifying
   	@Transactional
       @Query("update Contact c set c.name= ?1 , c.tellNumber=?2  where c.contact_id= ?3")
   	  public void updateContact( String contactName , String tellnumber ,  long id);
}
