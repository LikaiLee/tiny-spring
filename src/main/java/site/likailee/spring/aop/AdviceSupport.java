/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import site.likailee.spring.aop.pointcut.MethodMatcher;

/**
 * 代理的元数据
 *
 * @author likailee.llk
 * @version AdviceSupport.java 2020/11/20 Fri 1:56 PM likai
 */
public class AdviceSupport {
    /**
     * 代理对象
     */
    private TargetSource targetSource;
    /**
     * 方法拦截器
     */
    private MethodInterceptor methodInterceptor;
    /**
     * 方法匹配器
     * 只有方法匹配时才进行代理
     */
    private MethodMatcher methodMatcher;

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}
