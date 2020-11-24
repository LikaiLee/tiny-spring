/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.ioc.bean;

/**
 * @author likailee.llk
 * @version BeanPostProcessor.java 2020/11/23 Mon 4:54 PM likai
 */
public interface BeanPostProcessor {
    /**
     * before
     *
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);

    /**
     * after
     *
     * @param bean
     * @param beanName
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws IllegalAccessException, NoSuchFieldException, InstantiationException;
}
