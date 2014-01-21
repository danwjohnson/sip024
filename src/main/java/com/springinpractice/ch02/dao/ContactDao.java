/*
 * Spring in Practice Chapter 02
 * Recipe: 2.4
 * Class: ContactDao.java
 */

package com.springinpractice.ch02.dao;

import java.util.List;

import com.springinpractice.ch02.model.Contact;
import com.springinpractice.dao.Dao;

public interface ContactDao extends Dao<Contact>{

	List<Contact> findByEmail(String email);
	
} // end ContactDao interface
