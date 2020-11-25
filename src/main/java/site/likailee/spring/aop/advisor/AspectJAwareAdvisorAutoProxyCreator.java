/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.advisor;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import site.likailee.spring.aop.proxy.ProxyFactory;
import site.likailee.spring.aop.TargetSource;
import site.likailee.spring.aop.pointcut.Pointcut;
import site.likailee.spring.ioc.bean.BeanPostProcessor;
import site.likailee.spring.ioc.factory.AbstractBeanFactory;
import site.likailee.spring.ioc.factory.BeanFactory;

import java.util.List;

/**
 * BeanPostProcessor 可以在 Bean 生成后对 Bean 进行增强操作，在这里进行代理
 * BeanFactoryAware 实现对 BeanFactory 的感知，进而获取容器内的切点对象，并对切点对象进行代理
 *
 * @author likailee.llk
 * @version AspectJAwareAdvisorAutoProxyCreator.java 2020/11/24 Tue 2:29 PM likai
 */
public class AspectJAwareAdvisorAutoProxyCreator implements BeanPostProcessor, BeanFactoryAware {
    private AbstractBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = (AbstractBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws IllegalAccessException, NoSuchFieldException, InstantiationException {
        // 切点通知器，不进行代理
        if (bean instanceof AspectJExpressionPointcutAdvisor) {
            return bean;
        }
        // 方法拦截器，不进行代理
        if (bean instanceof MethodInterceptor) {
            return bean;
        }
        // 获取所有切点表达式对象，判断当前 bean 是否需要进行代理
        List<Object> advisors = beanFactory.getBeansForType(AspectJExpressionPointcutAdvisor.class);
        for (Object advisor : advisors) {
            // 获取切点
            Pointcut pointcut = ((AspectJExpressionPointcutAdvisor) advisor).getPointcut();
            // 获取拦截器
            Advice advice = ((AspectJExpressionPointcutAdvisor) advisor).getAdvice();
            // 匹配要拦截的类
            if (pointcut.getClassFilter().matches(bean.getClass())) {
                ProxyFactory adviceSupport = new ProxyFactory();
                // 设置 方法拦截器
                adviceSupport.setMethodInterceptor((MethodInterceptor) advice);
                // 设置 方法匹配器，在 AopProxy 中判断当方法匹配时才进行代理
                adviceSupport.setMethodMatcher(pointcut.getMethodMatcher());
                // 设置 需要代理的对象
                TargetSource targetSource = new TargetSource(bean, bean.getClass(), bean.getClass().getInterfaces());
                adviceSupport.setTargetSource(targetSource);
                // 返回代理对象
                return adviceSupport.getProxy();
                // return new JdkDynamicAopProxy(adviceSupport).getProxy();
            }
        }
        // 类不匹配，不进行代理
        return bean;
    }
}
