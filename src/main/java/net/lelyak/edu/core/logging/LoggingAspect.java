package net.lelyak.edu.core.logging;

import net.lelyak.edu.core.components.AbstractPageElement;
import net.lelyak.edu.core.localization.Localization;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

import java.util.Arrays;

@Aspect
public class LoggingAspect {

    /*@Before("execution(* net.lelyak.edu.core.components.TextField.sendText(..))")
    public void logBefore(JoinPoint joinPoint) {
        Logger.logInfo("Run function: " + joinPoint.getSignature().getName());
    }*/

    @After("execution(* net.lelyak.edu.core.components.TextField.sendText(..))")
    public void logAfter(JoinPoint joinPoint) {
        Object[] arguments = joinPoint.getArgs();
        String name = Arrays.toString(arguments);
        AbstractPageElement element = (AbstractPageElement) joinPoint.getThis();
        Logger.logInfo(Localization.getMessage(Localization.INPUT_SET_VALUE,
                name, element.getName(), element.getPage()));

    }

    /*@Around("execution(* net.lelyak.edu.core.components.TextField.sendText(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] arguments = joinPoint.getArgs();
        String name = Arrays.toString(arguments);
        AbstractPageElement element = (AbstractPageElement) joinPoint.getThis();
        Logger.logInfo("Before send text " + name + " to " + element.getName()
                + "in" + element.getPage());
        joinPoint.proceed();
        Logger.logInfo("After send text " + name + " to " + element.getName()
                + "in" + element.getPage());
    }*/
}