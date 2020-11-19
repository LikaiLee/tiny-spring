/**
 * https://likailee.site
 * CopyRight (c) 2020
 */

import org.junit.Test;
import site.likailee.spring.bean.BeanDefinition;
import site.likailee.spring.bean.PropertyValue;
import site.likailee.spring.bean.PropertyValues;
import site.likailee.spring.factory.AutowireCapableBeanFactory;
import site.likailee.spring.factory.BeanFactory;
import site.likailee.spring.io.ResourceLoader;
import site.likailee.spring.reader.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author likailee.llk
 * @version BeanFactoryTest.java 2020/11/19 Thu 10:29 AM likai
 */
public class BeanFactoryTest {

    @Test
    public void test() throws Exception {
        // 读取配置
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlReader.loadBeanDefinitions("beans.xml");
        Map<String, BeanDefinition> registry = xmlReader.getRegistry();

        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        // 在 BeanFactory 注册 BeanDefinition
        for (Map.Entry<String, BeanDefinition> entry : registry.entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
        // 获取 bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.hello();
    }
}
