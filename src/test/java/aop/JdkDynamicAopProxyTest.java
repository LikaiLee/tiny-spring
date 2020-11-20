/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package aop;

import org.junit.Test;
import service.HelloWorldService;
import site.likailee.spring.aop.AdviceSupport;
import site.likailee.spring.aop.JdkDynamicAopProxy;
import site.likailee.spring.aop.TargetSource;
import site.likailee.spring.context.ApplicationContext;
import site.likailee.spring.context.ClassPathXmlApplication;

/**
 * @author likailee.llk
 * @version JdkDynamicAopProxyTest.java 2020/11/20 Fri 2:57 PM likai
 */
public class JdkDynamicAopProxyTest {
    @Test
    public void test() throws Exception {
        ApplicationContext context = new ClassPathXmlApplication("beans.xml");
        HelloWorldService helloWorldService = (HelloWorldService) context.getBean("helloWorldService");
        // 被代理对象
        TargetSource targetSource = new TargetSource(helloWorldService, HelloWorldService.class);
        // 切面类
        LogInterceptor interceptor = new LogInterceptor();

        AdviceSupport adviceSupport = new AdviceSupport();
        adviceSupport.setTargetSource(targetSource);
        adviceSupport.setMethodInterceptor(interceptor);
        // 生成代理对象
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(adviceSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService) jdkDynamicAopProxy.getProxy();
        helloWorldServiceProxy.hello();
    }
}
