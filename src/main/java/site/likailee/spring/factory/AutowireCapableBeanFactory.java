/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.factory;

import site.likailee.spring.BeanDefinition;

/**
 * @author likailee.llk
 * @version AutowireCapableBeanFactory.java 2020/11/19 Thu 10:22 AM likai
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        try {
            // 生成 bean 实例
            Object bean = beanDefinition.getBeanClass().newInstance();
            return bean;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
