/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package context;

import site.likailee.spring.ioc.bean.BeanPostProcessor;

/**
 * @author likailee.llk
 * @version CustomBeanPostProcessor.java 2020/11/23 Mon 6:08 PM likai
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("custom " + beanName + " before");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("custom " + beanName + " after");
        return bean;
    }
}
