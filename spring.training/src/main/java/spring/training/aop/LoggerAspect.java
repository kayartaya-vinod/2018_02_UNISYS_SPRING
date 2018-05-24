package spring.training.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

	public LoggerAspect() {
		System.out.println("LogetAspect instantiated!");
	}
	
	// advice
	@Before("execution(* spring..ContactsDaoH*.get*(..))")
	public void logMethodInformation(JoinPoint jp) {
		System.out.printf("The method '%s' is invoked\n", 
				jp.getSignature().getName());
		System.out.println("Arguments supplied = " +
				Arrays.toString(jp.getArgs()));
		
	}
	
}
