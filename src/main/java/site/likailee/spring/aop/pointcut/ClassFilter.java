/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.pointcut;

/**
 * 类拦截器
 *
 * @author likailee.llk
 * @version ClassFilter.java 2020/11/24 Tue 12:44 PM likai
 */
public interface ClassFilter {
    /**
     * 判断 表达式 和 类 是否匹配
     *
     * @param targetClass
     * @return
     */
    boolean matches(Class targetClass);
}
