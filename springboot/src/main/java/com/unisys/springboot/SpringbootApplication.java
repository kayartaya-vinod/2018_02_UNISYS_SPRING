package com.unisys.springboot;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.unisys.springboot.model.Contact;

@EnableTransactionManagement
@PropertySource("classpath:database.properties")
@EnableWebMvc
@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class }) // -> implies automatic component scan within
																			// the same and sub packages
public class SpringbootApplication extends WebMvcConfigurerAdapter {

	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// enables the URL requests that are not mapped in spring
		// to be handled by TomCat (or the web container)
		// This is a must when "/" is mapped to the DispatcherServlet (which is
		// the default in Springboot application)
		configurer.enable();  
	}

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

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver irvr = new InternalResourceViewResolver();
		irvr.setPrefix("/WEB-INF/views/");
		irvr.setSuffix(".jsp");
		return irvr;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
