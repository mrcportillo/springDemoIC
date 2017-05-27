package hello;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by mrc on 15/04/17.
 */

@RestController
public class HelloController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @RequestMapping("/timeGreeting")
    public Greeting landingTime() {
        if (LocalTime.now().isAfter(LocalTime.of(12,0))) {
            return new Greeting(counter.incrementAndGet(), String.format(template, "night"));
        } else {
            return new Greeting(counter.incrementAndGet(), String.format(template, "morning"));
        }
    }

    @RequestMapping("/*")
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String error() {
        return "Bad url";
    }
}
