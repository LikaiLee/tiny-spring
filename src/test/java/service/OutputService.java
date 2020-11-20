package service; /**
 * https://likailee.site
 * CopyRight (c) 2020
 */

import org.junit.Assert;

/**
 * @author likailee.llk
 * @version service.OutputService.java 2020/11/20 Fri 9:54 AM likai
 */
public class OutputService {
    private HelloWorldService helloWorldService;

    public void output(String text) {
        Assert.assertNotNull(helloWorldService);
        System.out.println(text);
    }

    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }
}
