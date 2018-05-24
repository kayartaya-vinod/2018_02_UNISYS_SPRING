package spring.training.programs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.training.cfg.AppConfig2;
import spring.training.model.Contact;
import spring.training.repository.ContactsDao;
import spring.training.repository.DaoException;

public class P03_TestContactsDaoJdbcImpl {

	public static void main(String[] args) throws DaoException {
		
		AnnotationConfigApplicationContext ctx = 
				new AnnotationConfigApplicationContext(AppConfig2.class);
		
		ContactsDao dao = ctx.getBean(ContactsDao.class);
		Contact c1 = dao.getContact(1);
		
		System.out.println(c1);
		
		ctx.close();
		
	}
}
