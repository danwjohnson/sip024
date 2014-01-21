/*
 * Spring in Practice Chapter 02
 * Recipe: 2.4
 * Class: ContactServiceImpl.java
 */
package com.springinpractice.ch02.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springinpractice.ch02.dao.ContactDao;
import com.springinpractice.ch02.model.Contact;
import com.springinpractice.ch02.service.ContactService;

@Service
@Transactional
public class ContactServiceImpl implements ContactService{

	@Inject private ContactDao contactDao;
	
	public void createContact(Contact contact) {
	
		contactDao.create(contact);
		
	} // end createContact()
	
	
	public List<Contact> getContacts() {
		
		return contactDao.getAll();
		
	} // end getContacts()
	
	
	public List<Contact> getContactsByEmail(String email) {
		
		return contactDao.findByEmail(email);
				
	} // end getContactsByEmail
	
	
	public Contact getContact(Long id) {
		
		return contactDao.get(id);
				
	} // end getContact()
	
	
	public void updateContact(Contact contact) {
		
		contactDao.update(contact);
		
	} // end updateContact()
	
	
	public void deleteContact(Long id) {
		
		contactDao.deleteById(id);
		
	} // end deleteContact()
	
	
	
} // end ContactServiceImpl class