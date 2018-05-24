package spring.training.junit.tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.training.cfg.AppConfig4;
import spring.training.repository.ContactsDao;
import spring.training.repository.DaoException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig4.class })
public class ContactsDaoJdbcTemplateImplTestSuite {

	@Autowired
	@Qualifier("jdbcTemplateDao")
	ContactsDao dao;

	@Test
	public void UTC_01_01_testGetAllContacts() {
		assertNotNull(dao);
		try {
			assertEquals(1000, dao.getAllContacts().size());
		} catch (DaoException e) {
			fail(e.getMessage());
		}
	}

}
