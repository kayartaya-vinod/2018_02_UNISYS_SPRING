package spring.training.cfg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import spring.training.repository.ContactsDao;
import spring.training.repository.ContactsDaoJdbcImpl;

@Configuration
public class AppConfig2 {

	@Bean(name = { "conn", "connection" })
	public Connection connection() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		String url = "jdbc:h2:tcp://localhost/~/UNISYS_SPRING_FEB_2018";
		String user = "sa";
		String password = "";
		return DriverManager.getConnection(url, user, password);
	}

	@Bean(autowire = Autowire.BY_TYPE)
	public ContactsDao jdbcDao() {
		return new ContactsDaoJdbcImpl();
	}
}
