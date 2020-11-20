/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.ioc.io;

import java.net.URL;

/**
 * @author likailee.llk
 * @version ResourceLoader.java 2020/11/19 Thu 3:41 PM likai
 */
public class ResourceLoader {
    /**
     * 获取输入流
     *
     * @param location
     * @return
     */
    public Resource getResource(String location) {
        URL resource = this.getClass().getClassLoader().getResource(location);
        return new UrlResource(resource);
    }
}
