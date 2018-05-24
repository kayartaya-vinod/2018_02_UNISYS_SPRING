package com.unisys.springboot.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.unisys.springboot.model.Contact;

@Repository("hibernateTemplateDao")
@SuppressWarnings("unchecked")
public class ContactsDaoHibernateTemplateImpl implements ContactsDao {

	@Autowired(required = false)
	private HibernateTemplate template;

	@Override
	public void addContact(Contact contact) throws DaoException {
		template.persist(contact);
	}

	@Override
	public Contact getContact(int id) throws DaoException {
		return template.get(Contact.class, id);
	}

	@Override
	public void updateContact(Contact contact) throws DaoException {
		template.merge(contact);
	}

	@Override
	public void deleteContact(int id) throws DaoException {
		Contact contact = new Contact();
		contact.setId(id);
		template.delete(contact);
	}

	@Override
	public List<Contact> getAllContacts() throws DaoException {
		return (List<Contact>) template.find("from Contact order by id desc");
	}

	@Override
	public List<Contact> getContactsByCity(String city) throws DaoException {
		return (List<Contact>) template.find("from Contact where city = ?", city);
	}

	@Override
	public Contact getContactByEmail(String email) throws DaoException {
		List<Contact> list = (List<Contact>) template.find(
				"from Contact where email = ?", email);
		
		if (list.size() > 0)
			return list.get(0);
		
		return null;
	}

	@Override
	public Contact getContactByPhone(String phone) throws DaoException {
		List<Contact> list = (List<Contact>) template.find(
				"from Contact where phone = ?", phone);
		
		if (list.size() > 0)
			return list.get(0);
		
		return null;
	}

}
