/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.aop;

/**
 * 被代理的对象的相关信息
 *
 * @author likailee.llk
 * @version TargetSource.java 2020/11/20 Fri 1:54 PM likai
 */
public class TargetSource {
    /**
     * 真实对象
     */
    private Object target;
    /**
     * 对象所在的类
     */
    private Class<?>[] targetClass;

    public TargetSource(Object target, Class<?>... targetClass) {
        this.target = target;
        this.targetClass = targetClass;
    }

    public Class<?>[] getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?>[] targetClass) {
        this.targetClass = targetClass;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
