/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.context;

import site.likailee.spring.ioc.bean.BeanDefinition;
import site.likailee.spring.ioc.factory.AbstractBeanFactory;
import site.likailee.spring.ioc.factory.AutowireCapableBeanFactory;
import site.likailee.spring.ioc.io.ResourceLoader;
import site.likailee.spring.ioc.reader.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author likailee.llk
 * @version ClassPathXmlApplication.java 2020/11/20 Fri 12:17 PM likai
 */
public class ClassPathXmlApplication extends AbstractApplicationContext {
    /**
     * 配置文件位置
     */
    private String configLocation;

    public ClassPathXmlApplication(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplication(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        // 读取配置
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlReader.loadBeanDefinitions(configLocation);
        Map<String, BeanDefinition> registry = xmlReader.getRegistry();

        // 在 BeanFactory 注册 BeanDefinition
        for (Map.Entry<String, BeanDefinition> entry : registry.entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
    }
}
