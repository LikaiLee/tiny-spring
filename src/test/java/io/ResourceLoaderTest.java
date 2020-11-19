/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package io;

import org.junit.Assert;
import org.junit.Test;
import site.likailee.spring.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author likailee.llk
 * @version ResourceLoaderTest.java 2020/11/19 Thu 4:29 PM likai
 */
public class ResourceLoaderTest {

    @Test
    public void test() throws IOException {
        ResourceLoader loader = new ResourceLoader();
        InputStream inputStream = loader.getResource("beans.xml").getInputStream();
        Assert.assertNotNull(inputStream);
    }
}
