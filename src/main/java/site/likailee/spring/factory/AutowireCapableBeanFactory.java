/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.factory;

import site.likailee.spring.bean.BeanDefinition;
import site.likailee.spring.bean.PropertyValue;
import site.likailee.spring.bean.PropertyValues;

import java.lang.reflect.Field;

/**
 * @author likailee.llk
 * @version AutowireCapableBeanFactory.java 2020/11/19 Thu 10:22 AM likai
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        try {
            // 生成 bean 实例
            Object bean = createBeanInstance(beanDefinition);
            // 设置 bean 属性
            applyPropertyValues(bean, beanDefinition);
            return bean;
        } catch (InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 为 Bean 注入属性
     *
     * @param bean
     * @param beanDefinition
     */
    private void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue pv : propertyValues.getPropertyValues()) {
            Field field = bean.getClass().getDeclaredField(pv.getName());
            field.setAccessible(true);
            field.set(bean, pv.getValue());
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
