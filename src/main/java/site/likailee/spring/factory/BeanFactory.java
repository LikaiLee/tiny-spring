/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.factory;

import site.likailee.spring.BeanDefinition;

/**
 * @author likailee.llk
 * @version BeanFactory.java 2020/11/18 Wed 4:33 PM likai
 */
public interface BeanFactory {
    /**
     * 根据类名获取具体的类
     *
     * @param name
     * @return
     */
    Object getBean(String name);

    /**
     * 将 BeanDefinition 注册到 BeanFactory 中
     *
     * @param name
     * @param beanDefinition
     */
    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
