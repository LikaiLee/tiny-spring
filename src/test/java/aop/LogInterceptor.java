/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author likailee.llk
 * @version LogInterceptor.java 2020/11/20 Fri 2:54 PM likai
 */
public class LogInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long startTime = System.currentTimeMillis();
        System.out.println("---before AOP---");
        System.out.println("method: " + methodInvocation.getMethod().getName());
        Object proceed = methodInvocation.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("---after AOP---");
        System.out.println("time cost: " + (endTime - startTime));
        return proceed;
    }
}
