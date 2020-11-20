/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.ioc.reader;

/**
 * @author likailee.llk
 * @version BeanDefinitionReader.java 2020/11/19 Thu 3:44 PM likai
 */
public interface BeanDefinitionReader {
    /**
     * 根据 location 解析 Bean
     *
     * @param location 配置文件位置
     * @throws Exception
     */
    void loadBeanDefinitions(String location) throws Exception;
}
