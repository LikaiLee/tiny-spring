/**
 * https://likailee.site
 * CopyRight (c) 2020
 */

/**
 * @author likailee.llk
 * @version HelloWorldService.java 2020/11/19 Thu 10:29 AM likai
 */
public class HelloWorldService {
    private String text;

    private OutputService outputService;

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
