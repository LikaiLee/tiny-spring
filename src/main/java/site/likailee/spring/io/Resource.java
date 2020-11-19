/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package site.likailee.spring.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 定位资源的接口
 *
 * @author likailee.llk
 * @version Resource.java 2020/11/19 Thu 1:25 PM likai
 */
public interface Resource {
    /**
     * 获取 stream
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;
}
