/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import site.likailee.spring.aop.pointcut.MethodMatcher;

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
        // 方法拦截器
        MethodInterceptor methodInterceptor = adviceSupport.getMethodInterceptor();
        // 需要被代理的对象
        TargetSource target = adviceSupport.getTargetSource();
        MethodMatcher methodMatcher = adviceSupport.getMethodMatcher();
        // 拦截方法匹配
        if (methodMatcher != null && methodMatcher.matches(method, target.getTargetClass().getClass())) {
            // 调用代理方法
            MethodInvocation invocation = new ReflectiveMethodInvocation(target.getTarget(), method, args);
            return methodInterceptor.invoke(invocation);
        }
        // 拦截方法不匹配，调用原方法
        return method.invoke(target.getTarget(), args);
    }
}
