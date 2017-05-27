package hello;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.ContentResultMatchers;

import java.time.LocalTime;

/*
 * Created by mrc on 15/04/17.
 */


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHelloDefaultTest() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting?").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":3,\"content\":\"Hello, World!\"}"));
    }

//    @Test
//    public void getHelloTest() throws Exception {
//
//        mvc.perform(MockMvcRequestBuilders.get("/greeting?name=Jose").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{\"id\":2,\"content\":\"Hello, Jose!\"}"));
//    }
//
//    @Test
//    public void getLandingTime() throws Exception {
//        mvc.perform(MockMvcRequestBuilders.get("/timeGreeting").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(getJsonContent());
//    }

    private ResultMatcher getJsonContent() {
        if (LocalTime.now().isAfter(LocalTime.of(12, 0))) {
            return content().json("{\"id\":1,\"content\":\"Hello, night!\"}");
        } else {
            return  content().json("{\"id\":1,\"content\":\"Hello, morning!\"}");
        }
    }
}
