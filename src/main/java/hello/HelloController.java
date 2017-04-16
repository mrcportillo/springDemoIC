package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mrc on 15/04/17.
 */

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "hi you";
    }
}
