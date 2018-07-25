package com.rest.phonebook.model;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Phonebook {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String phonebookname;
	@OneToMany(mappedBy="phonebook",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	List<Contact> listContact=new ArrayList<>();
	@JsonIgnore
	@ManyToOne
	User  user;

	
	
	
	public Phonebook(String phonebookname) {
		super();
		this.phonebookname = phonebookname;
	}
	public Phonebook() {
		super();
	}
	public void addContact(Contact c) {
		listContact.add(c);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPhonebookname() {
		return phonebookname;
	}
	public void setPhonebookname(String phonebookname) {
		this.phonebookname = phonebookname;
	}
	public List<Contact> getListContact() {
		return listContact;
	}
	public void setListContact(List<Contact> listContact) {
		this.listContact = listContact;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	

}
