package com.rest.phonebook.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Contact {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long contact_id;
	private String name;
	private String tellNumber;
	@ManyToOne
	private Phonebook phonebook;

	public Contact() {
	}

	public Contact(String name, String tellNumber) {
		this.name = name;
		this.tellNumber = tellNumber;
	}

	public long getContact_id() {
		return contact_id;
	}

	public void setContact_id(long contact_id) {
		this.contact_id = contact_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getTellNumber() {
		return tellNumber;
	}

	public void setTellNumber(String tellNumber) {
		this.tellNumber = tellNumber;
	}

	public Phonebook getPhonebook() {
		return phonebook;
	}

	public void setPhonebook(Phonebook phonebook) {
		this.phonebook = phonebook;
	}

	

	

	
}
