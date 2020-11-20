/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.ioc.factory;

import site.likailee.spring.ioc.bean.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likailee.llk
 * @version AbstractBeanFactory.java 2020/11/18 Wed 4:39 PM likai
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String name) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();
        // bean 未实例化，进行实例化
        if (bean == null) {
            bean = doCreateBean(beanDefinition);
        }
        return bean;
    }

    /**
     * 将 BeanDefinition 注册到 BeanFactory 中
     * 仅建立映射关系，不实例化 Bean
     *
     * @param name           在 Map 中标识类的 Key
     * @param beanDefinition Bean 元数据
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        // 不进行 Bean 的实例化，否则可能导致循环依赖
        // name 实际就是配置里 bean 的 name
        beanDefinitionMap.put(name, beanDefinition);
    }

    /**
     * 手动实例化所有的 Bean
     *
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     */
    public void preInstantiateSingletons() throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        for (BeanDefinition beanDefinition : beanDefinitionMap.values()) {
            if (beanDefinition.getBean() == null) {
                synchronized (this) {
                    if (beanDefinition.getBean() == null) {
                        doCreateBean(beanDefinition);
                    }
                }
            }
        }
    }


    /**
     * 创建 Bean 实例，由具体的 BeanFactory 实现
     *
     * @param beanDefinition Bean 元数据
     * @return Bean 实例
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException, NoSuchFieldException;
}
