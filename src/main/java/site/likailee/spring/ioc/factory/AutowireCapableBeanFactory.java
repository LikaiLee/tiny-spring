/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.ioc.factory;

import site.likailee.spring.aop.advisor.BeanFactoryAware;
import site.likailee.spring.ioc.bean.BeanDefinition;
import site.likailee.spring.ioc.bean.BeanReference;
import site.likailee.spring.ioc.bean.PropertyValue;
import site.likailee.spring.ioc.bean.PropertyValues;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
        // 为 Bean 注入 BeanFactory
        if (bean instanceof BeanFactoryAware) {
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue pv : propertyValues.getPropertyValues()) {
            Object value = pv.getValue();
            // 注入依赖的 bean
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                // 从 factory 中取出依赖的 bean
                // 若 依赖的 bean 未实例化，则进行实例化
                value = getBean(beanReference.getName());
            }
            // 注入依赖的属性
            String propertyName = pv.getName();
            try {
                // 调用属性名对应的 setter 方法
                String methodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
                Method declaredMethod = bean.getClass().getDeclaredMethod(methodName, value.getClass());
                declaredMethod.setAccessible(true);
                declaredMethod.invoke(bean, value);
            } catch (NoSuchMethodException | InvocationTargetException e) {
                // 没有 set 方法，直接设置属性
                Field field = bean.getClass().getDeclaredField(propertyName);
                field.setAccessible(true);
                field.set(bean, value);
            }
        }
    }


}
