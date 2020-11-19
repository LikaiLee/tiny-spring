/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.reader;

import site.likailee.spring.bean.BeanDefinition;
import site.likailee.spring.io.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author likailee.llk
 * @version AbstractBeanDefinitionReader.java 2020/11/19 Thu 3:51 PM likai
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    /**
     * 保存读取到的 Bean，用于注入 BeanFactory
     */
    private final Map<String, BeanDefinition> registry;
    /**
     * 用于读取资源
     */
    private final ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
