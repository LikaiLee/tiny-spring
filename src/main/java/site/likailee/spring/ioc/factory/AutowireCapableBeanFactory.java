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

    /**
     * 为 Bean 注入属性
     *
     * @param bean
     * @param beanDefinition
     */
    @Override
    protected void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
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


}
