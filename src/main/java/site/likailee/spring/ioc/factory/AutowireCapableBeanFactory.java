/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.ioc.factory;

import site.likailee.spring.ioc.bean.BeanDefinition;
import site.likailee.spring.ioc.bean.BeanReference;
import site.likailee.spring.ioc.bean.PropertyValue;
import site.likailee.spring.ioc.bean.PropertyValues;

import java.lang.reflect.Field;

/**
 * @author likailee.llk
 * @version AutowireCapableBeanFactory.java 2020/11/19 Thu 10:22 AM likai
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 生成 bean 实例
        Object bean = createBeanInstance(beanDefinition);
        // 先创建后注入，所以不会存在两个循环依赖的 bean 创建死锁的问题
        beanDefinition.setBean(bean);
        // 设置 bean 属性
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    /**
     * 为 Bean 注入属性
     *
     * @param bean
     * @param beanDefinition
     */
    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue pv : propertyValues.getPropertyValues()) {
            Field field = bean.getClass().getDeclaredField(pv.getName());
            field.setAccessible(true);
            Object value = pv.getValue();
            // 注入依赖的 bean
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                // 从 factory 中取出依赖的 bean
                // 若 依赖的 bean 未实例化，则进行实例化
                value = getBean(beanReference.getName());
            }
            field.set(bean, value);
        }
    }

    /**
     * 创建 Bean 实例
     *
     * @param beanDefinition
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private Object createBeanInstance(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException {
        return beanDefinition.getBeanClass().newInstance();
    }
}
