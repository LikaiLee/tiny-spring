/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.bean;

/**
 * Bean 元数据
 *
 * @author likailee.llk
 * @version site.likailee.spring.bean.BeanDefinition.java 2020/11/18 Wed 4:29 PM likai
 */
public class BeanDefinition {
    /**
     * Bean 实例
     */
    private Object bean;
    /**
     * Bean 对应的类
     */
    private Class beanClass;
    /**
     * Bean 类名
     */
    private String beanClassName;
    /**
     * Bean 属性列表
     */
    private PropertyValues propertyValues = new PropertyValues();

    public BeanDefinition() {
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
        try {
            // 根据类名创建 Bean Class
            setBeanClass(Class.forName(beanClassName));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "bean=" + bean +
                ", beanClass=" + beanClass +
                ", beanClassName='" + beanClassName + '\'' +
                ", propertyValues=" + propertyValues +
                '}';
    }
}
