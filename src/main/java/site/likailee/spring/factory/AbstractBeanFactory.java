/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.factory;

import site.likailee.spring.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likailee.llk
 * @version AbstractBeanFactory.java 2020/11/18 Wed 4:39 PM likai
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    @Override
    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        // 模板方法，由子类构建 Bean
        Object bean = doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);
        // name 实际就是配置里的 id 或 name
        beanDefinitionMap.put(name, beanDefinition);
    }

    /**
     * 创建 Bean 实例，由具体的 BeanFactory 实现
     *
     * @param beanDefinition
     * @return
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition);
}
