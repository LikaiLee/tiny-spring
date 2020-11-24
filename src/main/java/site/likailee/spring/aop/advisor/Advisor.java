/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.advisor;

import org.aopalliance.aop.Advice;

/**
 * 通知器
 * 用于实现具体的方法拦截
 *
 * @author likailee.llk
 * @version Advisor.java 2020/11/24 Tue 2:01 PM likai
 */
public interface Advisor {
    /**
     * 获取拦截器
     *
     * @return
     */
    Advice getAdvice();
}
