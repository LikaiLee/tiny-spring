/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package aop;

import org.junit.Assert;
import org.junit.Test;
import service.HelloWorldService;
import service.impl.HelloWorldServiceImpl;
import site.likailee.spring.aop.aspect.AspectJExpressionPointcut;

/**
 * @author likailee.llk
 * @version AspectJExpressionPointcutTest.java 2020/11/24 Tue 1:03 PM likai
 */
public class AspectJExpressionPointcutTest {

    @Test
    public void testClass() {
        String expression = "execution(* service.*.*(..))";
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        boolean match = pointcut.getClassFilter().matches(HelloWorldService.class);
        Assert.assertTrue(match);
    }

    @Test
    public void testMethod() throws NoSuchMethodException {
        String expression = "execution(* service.*.*(..))";
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(expression);
        boolean match = pointcut.matches(HelloWorldServiceImpl.class.getDeclaredMethod("hello"), HelloWorldService.class);
        Assert.assertTrue(match);
    }
}
