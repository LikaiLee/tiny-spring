/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.ioc.bean;

/**
 * Bean 中引用的其他类
 *
 * @author likailee.llk
 * @version BeanReference.java 2020/11/20 Fri 10:04 AM likai
 */
public class BeanReference {
    private String name;
    // private Object bean;

    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
/*
    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }*/
}
