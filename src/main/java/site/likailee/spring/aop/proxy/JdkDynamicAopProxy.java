/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import site.likailee.spring.aop.AdviceSupport;
import site.likailee.spring.aop.TargetSource;
import site.likailee.spring.aop.pointcut.MethodMatcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK 代理
 *
 * @author likailee.llk
 * @version JdkDynamicAopProxy.java 2020/11/20 Fri 2:38 PM likai
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler {

    public JdkDynamicAopProxy(AdviceSupport adviceSupport) {
        super(adviceSupport);
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(), adviceSupport.getTargetSource().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 方法拦截器
        MethodInterceptor methodInterceptor = adviceSupport.getMethodInterceptor();
        // 需要被代理的对象
        TargetSource target = adviceSupport.getTargetSource();
        MethodMatcher methodMatcher = adviceSupport.getMethodMatcher();
        // 原方法
        MethodInvocation methodInvocation = new ReflectiveMethodInvocation(target.getTarget(), method, args);
        // 拦截方法匹配
        if (methodMatcher != null && methodMatcher.matches(method, target.getTargetClass())) {
            // 调用代理方法
            return methodInterceptor.invoke(methodInvocation);
        }
        // 拦截方法不匹配，调用原方法
        return method.invoke(target.getTarget(), args);
        // return methodInvocation.proceed();
    }
}
