/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author likailee.llk
 * @version JdkDynamicAopProxy.java 2020/11/20 Fri 2:38 PM likai
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    private AdviceSupport adviceSupport;

    public JdkDynamicAopProxy(AdviceSupport adviceSupport) {
        this.adviceSupport = adviceSupport;
    }

    @Override
    public Object getProxy() {
        // 需要被代理的对象
        Object target = adviceSupport.getTargetSource().getTarget();
        // 真实对象需要实现的接口
        // Class[] interfaces = {adviceSupport.getTargetSource().getTargetClass()};
        return Proxy.newProxyInstance(getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取切面
        MethodInterceptor methodInterceptor = adviceSupport.getMethodInterceptor();
        // 需要被代理的对象
        Object target = adviceSupport.getTargetSource().getTarget();
        // 切点
        MethodInvocation invocation = new ReflectiveMethodInvocation(target, method, args);
        // 返回代理对象
        return methodInterceptor.invoke(invocation);
    }
}
