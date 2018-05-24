package com.unisys.springboot.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.unisys.springboot.dao.ContactsDao;
import com.unisys.springboot.dao.DaoException;
import com.unisys.springboot.model.Contact;

@Controller
@RequestMapping("/mvc")
public class ContactsController {

	@Autowired
	ContactsDao dao;
	
	
	@RequestMapping(value="/save-contact", method=RequestMethod.POST)
	public String saveContact(@ModelAttribute Contact contact) throws DaoException {
		
		dao.addContact(contact);
		return "redirect:/mvc/all-contacts";
	}
	
	@RequestMapping(value="/all-contacts", method=RequestMethod.GET)
	public ModelAndView allContacts() throws DaoException {
		
		List<Contact> list = dao.getAllContacts();
		ModelAndView mav = new ModelAndView();
		mav.addObject("contacts", list);
		mav.setViewName("display-contacts");
		
		return mav;
	}
	
}





