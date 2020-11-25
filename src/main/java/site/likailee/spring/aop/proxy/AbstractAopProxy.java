/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop.proxy;

import site.likailee.spring.aop.AdviceSupport;

/**
 * 继承该类能够获取 adviceSupport 和 获取代理对象
 *
 * @author likailee.llk
 * @version AbstractAopProxy.java 2020/11/25 Wed 10:57 AM likai
 */
public abstract class AbstractAopProxy implements AopProxy {
    protected AdviceSupport adviceSupport;

    public AbstractAopProxy(AdviceSupport adviceSupport) {
        this.adviceSupport = adviceSupport;
    }
}
