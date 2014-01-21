/*
 * Spring in Practice Chapter 02
 * Recipe: 2.4
 * Class: HbnContactDao.java
 */

package com.springinpractice.ch02.dao.hbn;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springinpractice.ch02.dao.ContactDao;
import com.springinpractice.dao.hibernate.AbstractHbnDao;
import com.springinpractice.ch02.model.Contact;


@Repository
public class HbnContactDao extends AbstractHbnDao<Contact>
		implements ContactDao {

	@SuppressWarnings("unchecked")
	public List<Contact> findByEmail(String email) {
		
		return getSession()
				.getNamedQuery("findContactByEmail")
				.setString("email", "%" + email + "%")
				.list();
		
	} // end findByEmail()
	
} // end HbnContactDao class
