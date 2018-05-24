package spring.training.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.training.cfg.AppConfig6;
import spring.training.model.Contact;
import spring.training.repository.ContactsRepository;

public class P09_TestingContactsDaoRepository {
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConfig6.class);
		
		ContactsRepository repo = ctx.getBean(ContactsRepository.class);
		System.out.println("repo is an instanceof " + repo.getClass().getName());
		
		Contact c1 = repo.findById(1).get();
		System.out.println(c1);
		
		List<Contact> list = repo.findAll();
		System.out.printf("There are %d contacts\n", list.size());
		
		list = repo.findContactByCity("Dallas");
		System.out.printf("There are %d contacts in Dallas\n", list.size());
		
		list = repo.findContactByGender("Male");
		System.out.printf("There are %d male contacts\n", list.size());
		

		list = repo.getContactsByGenderAndCities("Male", "Dallas", "Brooklyn", "Chicago");
		System.out.printf("There are %d male contacts\n", list.size());
		
		ctx.close();
		
	}

}
