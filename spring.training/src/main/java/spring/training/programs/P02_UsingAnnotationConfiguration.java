package spring.training.programs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import spring.training.cfg.AppConfig1;
import spring.training.model.Greet;

public class P02_UsingAnnotationConfiguration {

	public static void main(String[] args) {
		ApplicationContext ctx;
		ctx = new AnnotationConfigApplicationContext(AppConfig1.class);

		Greet g1 = ctx.getBean("gr1", Greet.class);
		Greet g2 = ctx.getBean("greet2", Greet.class);
		Greet g3 = ctx.getBean("g3", Greet.class);
		
		System.out.printf("%s says %s!\n", g1.getUser(), g1.getMessage());
		System.out.printf("%s says %s!\n", g2.getUser(), g2.getMessage());
		System.out.printf("%s says %s!\n", g3.getUser(), g3.getMessage());
		
		((AbstractApplicationContext) ctx).close();
	}
}
