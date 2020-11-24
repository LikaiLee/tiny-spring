/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.pointcut;

/**
 * @author likailee.llk
 * @version PointCut.java 2020/11/24 Tue 12:45 PM likai
 */
public interface Pointcut {
    /**
     * 获取切面类
     *
     * @return
     */
    ClassFilter getClassFilter();

    /**
     * 获取切面方法
     *
     * @return
     */
    MethodMatcher getMethodMatcher();
}
