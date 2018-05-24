package com.unisys.spring.mongodb.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unisys.spring.mongodb.demo.model.Contact;
import com.unisys.spring.mongodb.demo.repository.ContactsRepository;

@RestController
@RequestMapping("/rest/contacts")
public class ContactsResource {

	@Autowired
	ContactsRepository repo;

	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public List<Contact> getAll() {
		return repo.findAll();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET, 
			produces= {"application/json", "application/xml"})
	public Contact getOne(@PathVariable String id) {
		return repo.findOne(id);
	}
}








