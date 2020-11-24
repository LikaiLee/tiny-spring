/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.advisor;

import org.aopalliance.aop.Advice;
import site.likailee.spring.aop.pointcut.AspectJExpressionPointcut;
import site.likailee.spring.aop.pointcut.Pointcut;

/**
 * AspectJ 切点表达式通知器
 * 表达式、拦截器都在 XML 中定义
 *
 * @author likailee.llk
 * @version AspectJExpressionPointcutAdvisor.java 2020/11/24 Tue 2:05 PM likai
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {
    /**
     * 切点
     */
    private AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    /**
     * 拦截器
     */
    private Advice advice;

    public void setExpression(String expression) {
        this.pointcut.setExpression(expression);
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    @Override
    public Pointcut getPointcut() {
        return pointcut;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }
}
