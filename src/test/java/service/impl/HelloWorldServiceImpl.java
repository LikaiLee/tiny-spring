/**
 * https://likailee.site
 * CopyRight (c) 2020
 */
package service.impl;

import service.HelloWorldService;
import service.OutputService;

/**
 * @author likailee.llk
 * @version HelloWorldServiceImpl.java 2020/11/20 Fri 2:49 PM likai
 */
public class HelloWorldServiceImpl implements HelloWorldService {
    private String text;

    private OutputService outputService;

    @Override
    public void hello() {
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }

}
