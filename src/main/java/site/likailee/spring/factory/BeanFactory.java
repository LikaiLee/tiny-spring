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
     * 根据类的标识符获取具体的类
     *
     * @param name 在 Map 中标识类的 Key
     * @return Bean
     */
    Object getBean(String name);

    /**
     * 将 BeanDefinition 注册到 BeanFactory 中
     *
     * @param name           在 Map 中标识类的 Key
     * @param beanDefinition Bean 元数据
     */
    void registerBeanDefinition(String name, BeanDefinition beanDefinition);
}
