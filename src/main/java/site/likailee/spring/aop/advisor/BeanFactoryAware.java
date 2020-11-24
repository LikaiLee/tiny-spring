/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.advisor;

import site.likailee.spring.ioc.factory.BeanFactory;

/**
 * 实现该接口可使 Bean 拥有获取 BeanFactory 的能力
 *
 * @author likailee.llk
 * @version BeanFactoryAware.java 2020/11/24 Tue 2:28 PM likai
 */
public interface BeanFactoryAware {
    /**
     * 设置 BeanFactory
     *
     * @param beanFactory
     */
    void setBeanFactory(BeanFactory beanFactory);
}
