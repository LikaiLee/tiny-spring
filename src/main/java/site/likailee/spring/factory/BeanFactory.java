/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.factory;

import site.likailee.spring.bean.BeanDefinition;

/**
 * @author likailee.llk
 * @version BeanFactory.java 2020/11/18 Wed 4:33 PM likai
 */
public interface BeanFactory {
    /**
     * 根据类的标识符获取具体的类
     * 若 Bean 未实例化，则进行实例化
     *
     * @param name 在 Map 中标识类的 Key
     * @return Bean
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     */
    Object getBean(String name) throws IllegalAccessException, NoSuchFieldException, InstantiationException;

    /**
     * 将 BeanDefinition 注册到 BeanFactory 中
     * 仅建立映射关系，不实例化 Bean
     *
     * @param name           在 Map 中标识类的 Key
     * @param beanDefinition Bean 元数据
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     */
    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws IllegalAccessException, NoSuchFieldException, InstantiationException;
}
