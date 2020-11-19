/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package xml;

import org.junit.Assert;
import org.junit.Test;
import site.likailee.spring.bean.BeanDefinition;
import site.likailee.spring.io.ResourceLoader;
import site.likailee.spring.reader.BeanDefinitionReader;
import site.likailee.spring.reader.XmlBeanDefinitionReader;

import java.util.Map;

/**
 * @author likailee.llk
 * @version XmlBeanDefinitionReaderTest.java 2020/11/19 Thu 4:42 PM likai
 */
public class XmlBeanDefinitionReaderTest {
    @Test
    public void test() throws Exception {
        XmlBeanDefinitionReader xmlReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlReader.loadBeanDefinitions("beans.xml");
        Map<String, BeanDefinition> registry = xmlReader.getRegistry();
        Assert.assertTrue(registry.size() > 0);
    }
}
