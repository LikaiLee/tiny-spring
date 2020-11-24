/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.advisor;

import site.likailee.spring.aop.pointcut.Pointcut;

/**
 * 切点通知器
 *
 * @author likailee.llk
 * @version PointcutAdvisor.java 2020/11/24 Tue 2:02 PM likai
 */
public interface PointcutAdvisor extends Advisor {
    /**
     * 获取切点
     *
     * @return
     */
    Pointcut getPointcut();
}
