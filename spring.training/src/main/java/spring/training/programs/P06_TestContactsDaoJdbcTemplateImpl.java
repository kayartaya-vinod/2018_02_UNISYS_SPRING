package spring.training.programs;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.training.cfg.AppConfig4;
import spring.training.model.Contact;
import spring.training.repository.ContactsDao;
import spring.training.repository.DaoException;

public class P06_TestContactsDaoJdbcTemplateImpl {

	public static void main(String[] args) throws DaoException {
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConfig4.class);
		
		ContactsDao dao = ctx.getBean("jdbcTemplateDao", ContactsDao.class);
		List<Contact> list = dao.getContactsByCity("Dallas");

		list.stream()
			.map(c->c.getFirstName() + " " + c.getLastName())
			.forEach(System.out::println );
		
		// --- or --- 
		
		for(Contact c: list) {
			System.out.println(c.getFirstName() + " " + c.getLastName());
		}
		
		ctx.close();
	}
}
