package spring.training.programs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.training.model.Greet;

public class P01_SpringAsFactory {

	public static void main(String[] args) {
		// a variable representing spring container
		ApplicationContext ctx;
		
		System.out.println("pass-1");
		
		// an object of a spring container
		ctx = new ClassPathXmlApplicationContext("context1.xml");
		
		System.out.println("pass-2");
		
		// ask for a bean from the container
		Greet g = ctx.getBean("gr1", Greet.class);
		System.out.println("pass-3");
		System.out.println(g.getUser() + " says " + g.getMessage());
		
		g = ctx.getBean("gr1", Greet.class);
		System.out.println("pass-4");
		System.out.println(g.getUser() + " says " + g.getMessage());
		
		
		g = ctx.getBean("gr2", Greet.class);
		System.out.println("pass-5");
		System.out.println(g.getUser() + " says " + g.getMessage());
		
		
		g = (Greet) ctx.getBean("gr3");
		System.out.println(g.getUser() + " says " + g.getMessage());
		
		((AbstractApplicationContext) ctx).close();
	}

}
