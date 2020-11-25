/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import site.likailee.spring.aop.AdviceSupport;
import site.likailee.spring.aop.TargetSource;
import site.likailee.spring.aop.pointcut.MethodMatcher;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Cglib 代理
 *
 * @author likailee.llk
 * @version CglibAopProxy.java 2020/11/25 Wed 10:59 AM likai
 */
public class CglibAopProxy extends AbstractAopProxy {
    public CglibAopProxy(AdviceSupport adviceSupport) {
        super(adviceSupport);
    }

    /**
     * 生成代理对象
     *
     * @return
     */
    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        // 设置父类 => 原对象
        enhancer.setSuperclass(adviceSupport.getTargetSource().getTargetClass());
        enhancer.setInterfaces(adviceSupport.getTargetSource().getInterfaces());
        // 设置回调方法
        enhancer.setCallback(new DynamicAdvisedInterceptor(adviceSupport));
        return enhancer.create();
    }

    /**
     * 该类实现的 MethodInterceptor 是 CallBack 的子类
     * 是 net.sf.cglib.proxy.MethodInterceptor
     * 而不是 aop 的 org.aopalliance.intercept.MethodInterceptor
     */
    private static class DynamicAdvisedInterceptor implements MethodInterceptor {
        private AdviceSupport adviceSupport;

        public DynamicAdvisedInterceptor(AdviceSupport adviceSupport) {
            this.adviceSupport = adviceSupport;
        }

        @Override
        public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            MethodMatcher methodMatcher = adviceSupport.getMethodMatcher();
            TargetSource targetSource = adviceSupport.getTargetSource();
            // 原方法
            ReflectiveMethodInvocation methodInvocation = new CglibMethodInvocation(targetSource.getTarget(), method, args, methodProxy);
            // 方法匹配
            if (methodMatcher != null && methodMatcher.matches(method, targetSource.getTargetClass())) {
                // 调用拦截器的 invoke 方法
                return adviceSupport.getMethodInterceptor().invoke(methodInvocation);
            }
            // 调用原方法
            return method.invoke(targetSource.getTarget(), args);
            // return methodInvocation.proceed();
        }
    }


    /**
     * 代理原对象的方法
     */
    private static class CglibMethodInvocation extends ReflectiveMethodInvocation {
        /**
         * 方法代理
         */
        private final MethodProxy methodProxy;

        public CglibMethodInvocation(Object target, Method method, Object[] args, MethodProxy methodProxy) {
            super(target, method, args);
            this.methodProxy = methodProxy;
        }

        @Override
        public Object proceed() throws Throwable {
            // 调用原始对象的方法
            return this.methodProxy.invoke(this.target, this.args);
        }
    }
}
