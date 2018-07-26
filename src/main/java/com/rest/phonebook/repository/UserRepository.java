package com.rest.phonebook.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.rest.phonebook.model.Phonebook;
import com.rest.phonebook.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	public User findByUsername(String username);

	@Query("select p from Phonebook p where user_userId=id")
	public List<Phonebook> listOfPhonebookForUser(@Param("id") long id);

	@Modifying
	@Transactional
	@Query("update User u set u.username= ?1  where u.userId= ?2")
	public void updateUserName(String userName, long userId);

}
