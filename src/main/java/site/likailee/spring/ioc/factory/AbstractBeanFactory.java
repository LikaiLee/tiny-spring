/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.ioc.factory;

import site.likailee.spring.ioc.bean.BeanDefinition;
import site.likailee.spring.ioc.bean.BeanPostProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author likailee.llk
 * @version AbstractBeanFactory.java 2020/11/18 Wed 4:39 PM likai
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

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
            initializeBean(bean, name);
        }
        return bean;
    }

    /**
     * 每个 bean 初始化时都会通过每个 BeanPostProcessor
     *
     * @param bean
     * @param name
     */
    protected void initializeBean(Object bean, String name) {
        for (BeanPostProcessor processor : beanPostProcessors) {
            bean = processor.postProcessBeforeInitialization(bean, name);
        }
        // call initialize method
        for (BeanPostProcessor processor : beanPostProcessors) {
            bean = processor.postProcessAfterInitialization(bean, name);
        }
    }

    /**
     * 创建 Bean 实例并设置属性
     *
     * @param beanDefinition Bean 元数据
     * @return Bean 实例
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     */
    protected Object doCreateBean(BeanDefinition beanDefinition) throws InstantiationException, IllegalAccessException, NoSuchFieldException {
        // 生成 bean 实例
        Object bean = createBeanInstance(beanDefinition);
        // 先创建后注入，所以不会存在两个循环依赖的 bean 创建死锁的问题
        beanDefinition.setBean(bean);
        // 设置 bean 属性
        applyPropertyValues(bean, beanDefinition);
        return bean;
    }

    /**
     * 为 bean 注入属性，由具体的 BeanFactory 子类实现
     *
     * @param bean
     * @param beanDefinition
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    protected abstract void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException, InstantiationException;

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
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            BeanDefinition beanDefinition = entry.getValue();
            if (beanDefinition.getBean() == null) {
                synchronized (this) {
                    if (beanDefinition.getBean() == null) {
                        // doCreateBean(beanDefinition);
                        getBean(entry.getKey());
                    }
                }
            }
        }

    }

    /**
     * 获取 BeanPostProcessor 的子类或同类
     *
     * @param beanPostProcessorClass
     * @return
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     */
    public List<Object> getBeansForType(Class<BeanPostProcessor> beanPostProcessorClass) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        List<Object> beans = new ArrayList<>();
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            Class clazz = entry.getValue().getBeanClass();
            // 获取 beanDefinitionMap 中的 BeanPostProcessor
            if (beanPostProcessorClass.isAssignableFrom(clazz)) {
                beans.add(getBean(entry.getKey()));
            }
        }
        return beans;
    }

    /**
     * 新增 BeanPostProcessor
     *
     * @param beanPostProcessor
     */
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.add(beanPostProcessor);
    }
}
