package spring.training.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.hibernate5.HibernateTemplate;

import spring.training.cfg.AppConfig5;
import spring.training.model.Contact;

public class P07_TestingHibernateTemplate {

	static HibernateTemplate template;
	
	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppConfig5.class);
		
		template = ctx.getBean(HibernateTemplate.class);
		
		// printContactById(1); // 1 --> id
		// updateContactCityTo(2, "LA"); // 2 --> id, "London" --> city
		printContactsInCity("Brooklyn");
		
		ctx.close();
		
	}

	@SuppressWarnings("unchecked")
	static void printContactsInCity(String city) {
		List<Contact> list = (List<Contact>) template.find(
				"from Contact where city = ?0", city);
		
		list.forEach(c -> System.out.printf(
				"%s is from %s\n", c.getFirstName(), c.getCity()));
	}

	static void updateContactCityTo(int id, String city) {
		Contact c1 = template.get(Contact.class, id);
		System.out.println(c1.getCity());
		
		c1.setCity(city);
		template.merge(c1.getCity());
		
		c1 = template.get(Contact.class, id);
		System.out.println(c1);
	}

	static void printContactById(int id) {
		Contact c1 = template.get(Contact.class, id);
		System.out.println(c1);
	}
}










