package cn.tedu.store.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeEclapsedAspect {
		
	@Before("execution(* cn.tedu.store.service.*.*(..))")
	public void doBefore(JoinPoint jp) {
		// TODO doSomthing
	}
	
	@After("execution(* cn.tedu.store.service.*.*(..))")
	public void doAfter() {
		//TODO doSomething.....
	}
	
	@Around("execution(* cn.tedu.store.service.*.*(..))")
	public void doAround(ProceedingJoinPoint pjp) 
	    throws Throwable {
	    // doBefore, do something...

	    pjp.proceed();

	    // doAfter, do something...
	}
}
