/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.pointcut;

import java.lang.reflect.Method;

/**
 * 方法匹配器
 *
 * @author likailee.llk
 * @version MethodMatcher.java 2020/11/24 Tue 12:44 PM likai
 */
public interface MethodMatcher {
    /**
     * 判断 表达式 和 方法 是否匹配
     *
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method, Class targetClass);
}
