package com.unisys.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unisys.springboot.dao.ContactsDao;
import com.unisys.springboot.dao.DaoException;
import com.unisys.springboot.model.Contact;
import com.unisys.springboot.model.ContactList;

@CrossOrigin(origins = { "*" })
@RestController
@RequestMapping("/rest/contacts")
public class ContactsResource {

	@Autowired
	ContactsDao dao;

	@RequestMapping(method = RequestMethod.GET, params = { "city" }, produces = "application/json")
	public List<Contact> getAll(@RequestParam String city) throws DaoException {
		if (city == null)
			return dao.getAllContacts();
		else
			return dao.getContactsByCity(city);
	}

	@RequestMapping(method = RequestMethod.GET, params = { "city" }, produces = "application/xml")
	public ContactList getAllAsXml(@RequestParam String city) throws DaoException {
		if (city == null)
			return new ContactList(dao.getAllContacts());
		else
			return new ContactList(dao.getContactsByCity(city));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = { "application/json", "application/xml" })
	public Contact getOneContact(@PathVariable int id) throws DaoException {
		return dao.getContact(id);
	}

	@RequestMapping(method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE,
			"application/xml" }, consumes = { MediaType.APPLICATION_JSON_VALUE, "application/xml" })
	public Contact saveContactDetails(@RequestBody Contact contact) throws DaoException {
		dao.addContact(contact);
		return contact;
	}

}
