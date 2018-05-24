package spring.training.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.training.cfg.AppConfig5;
import spring.training.model.Contact;
import spring.training.repository.ContactsDao;
import spring.training.repository.DaoException;

public class P08_TestingContactsDaoHibernateTemplateImpl {
	public static void main(String[] args) throws DaoException {
		AnnotationConfigApplicationContext ctx =
				new AnnotationConfigApplicationContext(AppConfig5.class);
		
		ContactsDao dao = ctx.getBean("hibernateTemplateDao", ContactsDao.class);
		ContactsDao dao1 = ctx.getBean("jdbcTemplateDao", ContactsDao.class);

		System.out.println("dao is an instanceof " + dao.getClass().getName());
		System.out.println("dao1 is an instanceof " + dao1.getClass().getName());
		
		Contact c1 = dao.getContact(1);
		System.out.println(c1);
		
		c1.setEmail("kayartaya.vinod@gmail.com");
		dao.updateContact(c1);
		System.out.println("Email updated successfully!");
		
		
		String city = "Dallas";
		List<Contact> list = dao.getContactsByCity(city);
		System.out.printf("There are %d contacts from '%s' city\n", list.size(), city);
		
		ctx.close();
	}
}







