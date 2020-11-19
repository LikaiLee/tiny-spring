/**
 * https://likailee.site
 * CopyRight (c) 2020
 */

import org.junit.Test;
import site.likailee.spring.BeanDefinition;
import site.likailee.spring.PropertyValue;
import site.likailee.spring.PropertyValues;
import site.likailee.spring.factory.AutowireCapableBeanFactory;
import site.likailee.spring.factory.BeanFactory;

/**
 * @author likailee.llk
 * @version BeanFactoryTest.java 2020/11/19 Thu 10:29 AM likai
 */
public class BeanFactoryTest {

    @Test
    public void test() {
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        // 在 BeanFactory 注册 BeanDefinition
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("HelloWorldService");
        // 设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("text", "hello world"));
        beanDefinition.setPropertyValues(propertyValues);
        // 注册 bean
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);
        // 获取 bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.hello();
    }
}
