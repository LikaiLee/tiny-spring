/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.context;

import site.likailee.spring.ioc.factory.AbstractBeanFactory;

/**
 * @author likailee.llk
 * @version AbstractApplicationContext.java 2020/11/20 Fri 12:16 PM likai
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception {

    }

    @Override
    public Object getBean(String name) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        return beanFactory.getBean(name);
    }
}
