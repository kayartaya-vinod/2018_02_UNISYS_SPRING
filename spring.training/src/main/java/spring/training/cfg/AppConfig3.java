package spring.training.cfg;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = { "spring.training.repository" })
@PropertySource("classpath:database.properties")
public class AppConfig3 {

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
