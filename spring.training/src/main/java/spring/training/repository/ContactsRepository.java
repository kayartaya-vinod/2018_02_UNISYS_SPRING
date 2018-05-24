package spring.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import spring.training.model.Contact;

public interface ContactsRepository extends JpaRepository<Contact, Integer>{
	
	public List<Contact> findContactByCity(String city);
	
	public List<Contact> findContactByGender(String gender);
	
	@Query("from Contact where gender = ?1 and city in ?2")
	public List<Contact> getContactsByGenderAndCities(String gender, String...cities);
}
