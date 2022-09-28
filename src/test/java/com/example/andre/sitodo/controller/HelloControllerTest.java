package com.example.andre.sitodo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.TEXT_HTML;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;
	
    @Test
    void showHello_ok() {
        // [Setup]
        HelloController helloController = new HelloController();

        // [Exercise]
        String result = helloController.showHello();

        // [Verify]
        assertEquals("hello", result);

        // [Teardown]
        // Do nothing
    } 
    
    @Test
    void showHello_okResponse() throws Exception {
        mockMvc.perform(get("/hello")).andExpectAll(
            status().isOk(),
            content().contentTypeCompatibleWith(TEXT_HTML),
            content().encoding(UTF_8),
            view().name("hello")
        );
    }
}