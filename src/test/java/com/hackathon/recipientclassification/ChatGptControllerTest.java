package com.hackathon.recipientclassification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ChatGptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGeneration() throws Exception {
        mockMvc.perform(get("/ai/generate")
                        .param("message", "Tell me a joke"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.generation").isNotEmpty());
    }
}