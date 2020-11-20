/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.ioc.reader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import site.likailee.spring.ioc.bean.BeanDefinition;
import site.likailee.spring.ioc.bean.BeanReference;
import site.likailee.spring.ioc.bean.PropertyValue;
import site.likailee.spring.ioc.io.ResourceLoader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author likailee.llk
 * @version XmlBeanDefinitionReader.java 2020/11/19 Thu 4:22 PM likai
 */
public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    /**
     * 解析 XML 为 BeanDefinition
     *
     * @param inputStream 输入流
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);
        parseBeanDefinitions(doc);
        inputStream.close();
    }

    /**
     * 解析 XML 树
     *
     * @param doc XML 文档
     */
    private void parseBeanDefinitions(Document doc) {
        Element root = doc.getDocumentElement();
        NodeList nodes = root.getChildNodes();
        // 遍历所有子节点
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }
    }

    /**
     * 将每个节点转化为 BeanDefinition
     *
     * @param ele node of XML
     */
    private void processBeanDefinition(Element ele) {
        // Bean 名称
        String name = ele.getAttribute("name");
        // Bean 对应的类
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        // 注入 Bean 属性
        processProperty(beanDefinition, ele);
        beanDefinition.setBeanClassName(className);
        // 保存 beanDefinition
        getRegistry().put(name, beanDefinition);
    }

    /**
     * 设置 Bean 的属性
     *
     * @param beanDefinition
     * @param ele
     */
    private void processProperty(BeanDefinition beanDefinition, Element ele) {
        NodeList properties = ele.getElementsByTagName("property");
        for (int i = 0; i < properties.getLength(); i++) {
            Node node = properties.item(i);
            if (node instanceof Element) {
                Element property = (Element) node;
                String name = property.getAttribute("name");
                String value = property.getAttribute("value");
                // property 为属性
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                }
                // property 为 bean
                else {
                    String ref = property.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    // 先不实例化依赖的 bean
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}
