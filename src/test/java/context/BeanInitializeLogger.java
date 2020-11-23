/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package context;

import site.likailee.spring.ioc.bean.BeanPostProcessor;

/**
 * @author likailee.llk
 * @version BeanInitializeLogger.java 2020/11/23 Mon 6:02 PM likai
 */
public class BeanInitializeLogger implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("init " + beanName + " start");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("init " + beanName + " end");
        return bean;
    }
}
