package spring.training.cfg;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import spring.training.model.Contact;

@EnableTransactionManagement
//@EnableAspectJAutoProxy
@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan(basePackages = { "spring.training.repository", "spring.training.aop" })
public class AppConfig5 {
	
	@Bean
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

	@Bean
	public HibernateTemplate template(SessionFactory sessionFactory) {
		return new HibernateTemplate(sessionFactory);
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
		LocalSessionFactoryBean sf = new LocalSessionFactoryBean();
		sf.setDataSource(dataSource);

		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		props.setProperty("hibernate.show_sql", "false");
		props.setProperty("hibernate.format_sql", "true");

		sf.setHibernateProperties(props);
		sf.setAnnotatedClasses(Contact.class);

		return sf;
	}

	@Bean
	public DataSource dataSource(Environment env) {
		BasicDataSource bds = new BasicDataSource();
		// db config:
		bds.setDriverClassName(env.getProperty("db.driver"));
		bds.setUrl(env.getProperty("db.url"));
		bds.setUsername(env.getProperty("db.username"));
		bds.setPassword(env.getProperty("db.password"));
		// pool config:
		bds.setInitialSize(50);
		bds.setMinIdle(5);
		bds.setMaxIdle(50);
		bds.setMaxTotal(100);
		return bds;
	}

}
