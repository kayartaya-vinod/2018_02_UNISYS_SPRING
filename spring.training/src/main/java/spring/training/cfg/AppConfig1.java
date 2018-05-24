package spring.training.cfg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import spring.training.model.Greet;

// substitution for the context1.xml
@Configuration
public class AppConfig1 {

	// When spring container is created, the container will instantiate this
	// class, calls each of @Bean methods, collects the beans and stores them
	// with the method names (for id)
	@Bean
	public Greet gr1() {
		System.out.println("gr1() called.");
		return new Greet();
	}

	@Scope("prototype")
	@Bean(name = "greet2") // overrides the name "gr2"
	public Greet gr2() {
		System.out.println("gr2() called.");
		return new Greet("KVinod", "Welcome to Spring!");
	}

	@Bean(name = { "g3", "gr3", "greet3" })
	public Greet gr3() {
		System.out.println("gr3() called.");
		Greet g = new Greet();
		g.setUser("Kumar");
		g.setMessage("Howdy!");
		return g;
	}
}
