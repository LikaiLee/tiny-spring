/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.proxy;

import site.likailee.spring.aop.AdviceSupport;

import java.util.Arrays;

/**
 * @author likailee.llk
 * @version ProxyFactory.java 2020/11/25 Wed 12:35 PM likai
 */
public class ProxyFactory extends AdviceSupport implements AopProxy {

    @Override
    public Object getProxy() {
        return createAopProxy().getProxy();
    }

    protected final AopProxy createAopProxy() {
        // 未实现接口，使用 CGLIB 代理
        if (this.getTargetSource().getInterfaces().length == 0) {
            System.out.println("CGLIB proxy " + this.getTargetSource().getTarget().getClass().getName());
            return new CglibAopProxy(this);
        }
        System.out.println("JDK proxy " + this.getTargetSource().getTarget().getClass().getName());
        // 实现接口，使用 JDK 代理
        return new JdkDynamicAopProxy(this);
    }
}
