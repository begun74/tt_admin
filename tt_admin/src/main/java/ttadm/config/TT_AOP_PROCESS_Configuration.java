package ttadm.config;

import java.util.ArrayList;
import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import ttadm.model.Tail;
import ttadm.util.CalculatePrice_Opt_Rozn_forTails;

@Configuration
@EnableAspectJAutoProxy
@Aspect
public class TT_AOP_PROCESS_Configuration {

	@Around("execution(* ttadm.service.*.*(..)) && @annotation(ttadm.annotation.ProcessTail) && args(ttadm.model.Tail)")
	public void processTail(ProceedingJoinPoint   pjp) throws Throwable {
		
		Object[] args = pjp.getArgs();
		
		Tail tail = CalculatePrice_Opt_Rozn_forTails.calculate((Tail)pjp.getArgs()[0]);

		pjp.getArgs()[0] = tail;
		pjp.proceed(args);
		//return result;
	}

	@Around("execution(* ttadm.service.*.*(..)) && @annotation(ttadm.annotation.ProcessTail) && args(java.util.Collection<ttadm.model.Tail>)")
	public void processCollectionTails(ProceedingJoinPoint   pjp) throws Throwable {
		
		Object[] args = pjp.getArgs();
		Collection<Tail> collTails = (Collection<Tail>)args[0];
		Collection<Tail> newCollTails = new ArrayList(); 
		
		//long time = System.currentTimeMillis();
		
		//System.out.println(collTails);
		for(Tail tail: collTails)
		{
			tail = CalculatePrice_Opt_Rozn_forTails.calculate(tail);
			newCollTails.add(tail);
		}
		pjp.getArgs()[0] = newCollTails;
		pjp.proceed(args);
		
		//time = (System.currentTimeMillis() - time)/1000;
		//System.out.println("time - " + time+" sec.");

		//System.out.println(newCollTails);
		//return result;
	}

}
