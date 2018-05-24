package spring.training.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.training.cfg.AppConfig3;
import spring.training.model.Contact;
import spring.training.repository.ContactsDao;
import spring.training.repository.DaoException;

public class P04_GetAllContacts {

	public static void main(String[] args) throws DaoException {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConfig3.class);
		
		ContactsDao dao = ctx.getBean(ContactsDao.class);
		List<Contact> list = dao.getAllContacts();
		System.out.printf("There are %d contacts\n", list.size());
		
		ctx.close();
	}
}
