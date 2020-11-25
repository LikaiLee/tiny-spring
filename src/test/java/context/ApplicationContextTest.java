/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package context;

import org.junit.Test;
import service.HelloWorldService;
import service.WalkService;
import site.likailee.spring.context.ApplicationContext;
import site.likailee.spring.context.ClassPathXmlApplication;

/**
 * @author likailee.llk
 * @version ApplicationContextTest.java 2020/11/20 Fri 12:26 PM likai
 */
public class ApplicationContextTest {
    @Test
    public void test() throws Exception {
        ApplicationContext context = new ClassPathXmlApplication("beans.xml");
        WalkService walkService = (WalkService) context.getBean("walkService");
        walkService.walk();
        HelloWorldService helloWorldService = (HelloWorldService) context.getBean("helloWorldService");
        helloWorldService.hello();
    }
}
