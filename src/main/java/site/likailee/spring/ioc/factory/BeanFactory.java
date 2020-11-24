/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.ioc.factory;

/**
 * @author likailee.llk
 * @version BeanFactory.java 2020/11/18 Wed 4:33 PM likai
 */
public interface BeanFactory {
    /**
     * 根据类的标识符获取具体的类
     * 若 Bean 未实例化，则进行实例化
     *
     * @param id 在 Map 中标识类的 Key
     * @return Bean
     * @throws IllegalAccessException
     * @throws NoSuchFieldException
     * @throws InstantiationException
     */
    Object getBean(String id) throws IllegalAccessException, NoSuchFieldException, InstantiationException;
}
