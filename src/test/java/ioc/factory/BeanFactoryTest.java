package ioc.factory; /**
 * https://likailee.site
 * CopyRight (c) 2020
 */

import org.junit.Test;
import service.HelloWorldService;
import site.likailee.spring.ioc.bean.BeanDefinition;
import site.likailee.spring.ioc.factory.AutowireCapableBeanFactory;
import site.likailee.spring.ioc.io.ResourceLoader;
import site.likailee.spring.ioc.reader.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author likailee.llk
 * @version ioc.factory.BeanFactoryTest.java 2020/11/19 Thu 10:29 AM likai
 */
public class BeanFactoryTest {

    @Test
    public void testLazy() throws Exception {
        // 读取配置
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlReader.loadBeanDefinitions("beans.xml");
        Map<String, BeanDefinition> registry = xmlReader.getRegistry();

        AutowireCapableBeanFactory beanFactory = new AutowireCapableBeanFactory();
        // 在 BeanFactory 注册 BeanDefinition
        for (Map.Entry<String, BeanDefinition> entry : registry.entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
        // 获取 bean，在 getBean() 里实例化
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.hello();
    }

    @Test
    public void testPreInstantiate() throws Exception {
        // 读取配置
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlReader.loadBeanDefinitions("beans.xml");
        Map<String, BeanDefinition> registry = xmlReader.getRegistry();

        AutowireCapableBeanFactory beanFactory = new AutowireCapableBeanFactory();
        // 在 BeanFactory 注册 BeanDefinition
        for (Map.Entry<String, BeanDefinition> entry : registry.entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
        // 手动实例化
        beanFactory.preInstantiateSingletons();
        // 获取 bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.hello();
    }
}
