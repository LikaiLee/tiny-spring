/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.aspect;

import java.lang.reflect.Method;

/**
 * @author likailee.llk
 * @version MethodMatcher.java 2020/11/24 Tue 12:44 PM likai
 */
public interface MethodMatcher {
    /**
     * 判断方法是否匹配
     *
     * @param method
     * @param targetClass
     * @return
     */
    boolean matches(Method method, Class targetClass);
}
