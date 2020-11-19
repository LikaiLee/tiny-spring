/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring;

/**
 * 用于 Bean 属性注入的 Key-Value 对
 *
 * @author likailee.llk
 * @version PropertyValue.java 2020/11/19 Thu 11:03 AM likai
 */
public class PropertyValue {
    /**
     * 属性名
     */
    private final String name;
    /**
     * 属性值
     */
    private final String value;

    public PropertyValue(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
