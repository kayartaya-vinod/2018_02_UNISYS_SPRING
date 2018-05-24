package spring.training.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import spring.training.model.Contact;

@Transactional(readOnly = true)
public interface ContactsDao {

	// CRUD OPERATIONS
	@Transactional(readOnly = false)
	public void addContact(Contact contact) throws DaoException;

	public Contact getContact(int id) throws DaoException;
	
	@Transactional(readOnly = false)
	public void updateContact(Contact contact) throws DaoException;

	@Transactional(readOnly = false)
	public void deleteContact(int id) throws DaoException;

	// QUERIES

	public List<Contact> getAllContacts() throws DaoException;

	public List<Contact> getContactsByCity(String city) throws DaoException;

	public Contact getContactByEmail(String email) throws DaoException;

	public Contact getContactByPhone(String phone) throws DaoException;

}
