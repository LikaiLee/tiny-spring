/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package aop;

import org.junit.Test;
import service.HelloWorldService;
import service.impl.HelloWorldServiceImpl;
import site.likailee.spring.aop.AdviceSupport;
import site.likailee.spring.aop.proxy.CglibAopProxy;
import site.likailee.spring.aop.TargetSource;
import site.likailee.spring.context.ApplicationContext;
import site.likailee.spring.context.ClassPathXmlApplication;

/**
 * @author likailee.llk
 * @version CglibAopProxyTest.java 2020/11/25 Wed 12:39 PM likai
 */
public class CglibAopProxyTest {
    @Test
    public void test() throws Exception {
        ApplicationContext context = new ClassPathXmlApplication("beans.xml");
        HelloWorldService helloWorldService = (HelloWorldService) context.getBean("helloWorldService");
        // 被代理对象
        TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldServiceImpl.class, HelloWorldService.class);
        // 切面类（拦截器 Advice）
        LogInterceptor interceptor = new LogInterceptor();

        AdviceSupport adviceSupport = new AdviceSupport();
        adviceSupport.setTargetSource(targetSource);
        adviceSupport.setMethodInterceptor(interceptor);

        // 方法匹配器
        // AspectJExpressionPointcut methodMatcher = new AspectJExpressionPointcut();
        // methodMatcher.setExpression("execution(* service.*.*(..))");
        // adviceSupport.setMethodMatcher(methodMatcher);

        CglibAopProxy cglibAopProxy = new CglibAopProxy(adviceSupport);
        HelloWorldService proxy = (HelloWorldService) cglibAopProxy.getProxy();
        proxy.hello();
    }
}
