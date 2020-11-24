/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package service.impl;

import org.junit.Assert;
import service.HelloWorldService;
import service.OutputService;

/**
 * @author likailee.llk
 * @version OutputServiceImpl.java 2020/11/24 Tue 4:45 PM likai
 */
public class OutputServiceImpl implements OutputService {
    private HelloWorldService helloWorldService;

    @Override
    public void output(String text) {
        Assert.assertNotNull(helloWorldService);
        System.out.println(text);
    }

    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }
}
