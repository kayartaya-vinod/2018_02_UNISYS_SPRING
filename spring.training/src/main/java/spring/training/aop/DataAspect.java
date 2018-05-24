package spring.training.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class DataAspect {

	@Around("execution(* *..*.*Email(String))")
	public Object convertInputToLowercase(ProceedingJoinPoint pjp) throws Throwable {
		// do some stuff before going to the target method
		Object[] args = pjp.getArgs();
		args = new Object[] { args[0].toString().toLowerCase() };
		
		// proceed to the target
		Object ret = pjp.proceed(args);
		
		// do some stuff after returning from the target method
		return ret;
	}

}
