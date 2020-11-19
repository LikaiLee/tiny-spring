/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 封装一个对象所有的 PropertyValue
 *
 * @author likailee.llk
 * @version PropertyValues.java 2020/11/19 Thu 11:05 AM likai
 */
public class PropertyValues {
    private final List<PropertyValue> propertyValues = new ArrayList<>();

    /**
     * 添加新属性
     *
     * @param pv
     */
    public void addPropertyValue(PropertyValue pv) {
        this.propertyValues.add(pv);
    }

    /**
     * 获取所有属性
     *
     * @return
     */
    public List<PropertyValue> getPropertyValues() {
        return this.propertyValues;
    }

}
