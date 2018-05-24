package spring.training.cfg;

import java.util.Random;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@PropertySource("classpath:database.properties")
@ComponentScan(basePackages = { "spring.training.repository" })
public class AppConfig4 {
	
	@Bean
//	@Scope("prototype")
	public String hello() {
//		System.out.println("hello() called!");
		return "Hello, world! - " + new Random().nextInt(1000);
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource ds) {
//		System.out.println("this.getClass().getName()=" + this.getClass().getName());
//		System.out.println("jdbcTemplate() called!");
//		System.out.println(this.hello());
//		System.out.println(this.hello());
//		System.out.println(this.hello());
//		System.out.println(this.hello());
//		System.out.println(this.hello());
		return new JdbcTemplate(ds);
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
