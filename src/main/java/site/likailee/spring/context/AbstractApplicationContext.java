/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.context;

import site.likailee.spring.ioc.bean.BeanPostProcessor;
import site.likailee.spring.ioc.factory.AbstractBeanFactory;

import java.util.List;

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
        // 注册 BeanDefinition
        loadBeanDefinitions(beanFactory);
        // 注册 BeanPostProcessor，确保 BeanPostProcessor 的实例化顺序优于普通的 Bean
        registerBeanPostProcessors(beanFactory);

        onRefresh();
    }

    protected void onRefresh() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        // 实例化 Bean
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 注册 BeanPostProcessor
     *
     * @param beanFactory
     */
    protected void registerBeanPostProcessors(AbstractBeanFactory beanFactory) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        // 获取 BeanPostProcessor 的类
        List<Object> beanPostProcessors = beanFactory.getBeansForType(BeanPostProcessor.class);
        // 往 beanPostProcessors 里添加 beanPostProcessor
        for (Object beanPostProcessor : beanPostProcessors) {
            beanFactory.addBeanPostProcessor((BeanPostProcessor) beanPostProcessor);
        }
    }

    /**
     * 读取配置并在 beanFactory 中注册 BeanDefinition
     *
     * @param beanFactory
     * @throws Exception
     */
    protected abstract void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception;

    @Override
    public Object getBean(String name) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        return beanFactory.getBean(name);
    }
}
