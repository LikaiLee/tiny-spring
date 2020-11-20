/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;

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
     * 切面
     */
    private MethodInterceptor methodInterceptor;

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
