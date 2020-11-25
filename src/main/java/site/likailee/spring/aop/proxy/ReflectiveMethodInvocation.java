/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.proxy;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * 被代理对象的方法，控制原方法的执行
 * 即拦截器 invoke() 中的 methodInvocation
 *
 * @author likailee.llk
 * @version ReflectiveMethodInvocation.java 2020/11/20 Fri 2:33 PM likai
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    /**
     * 被代理的真实对象
     */
    protected Object target;
    /**
     * 被代理的方法
     */
    protected Method method;
    /**
     * 方法参数
     */
    protected Object[] args;

    public ReflectiveMethodInvocation(Object target, Method method, Object[] args) {
        this.target = target;
        this.method = method;
        this.args = args;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    /**
     * 控制代理方法何时执行
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object proceed() throws Throwable {
        // 调用原始对象的方法
        return method.invoke(target, args);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
