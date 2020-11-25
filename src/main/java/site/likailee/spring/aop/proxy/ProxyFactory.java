/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.proxy;

import site.likailee.spring.aop.AdviceSupport;

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
        return new CglibAopProxy(this);
    }
}
