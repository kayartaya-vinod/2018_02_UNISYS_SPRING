package spring.training.cfg;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
@PropertySource("classpath:database.properties")
@EnableJpaRepositories(basePackages = { "spring.training.repository" })
public class AppConfig6 {

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean emf;
		emf = new LocalContainerEntityManagerFactoryBean();

		emf.setDataSource(dataSource);

		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		props.setProperty("hibernate.show_sql", "false");
		props.setProperty("hibernate.format_sql", "true");
		emf.setJpaProperties(props);

		emf.setPackagesToScan("spring.training.model");

		emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		return emf;
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
