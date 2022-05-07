package com.myTaskManager.taskmanager.controller;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

//@WebMvcTest(TaskProcessRestController.class)
class TaskProcessRestControllerIntTest {
 // WRITE 1 FOR POST
    @Autowired
    private MockMvc mockMvc;

   // @MockBean
  //  private TaskProcessService taskProcessService;

    //@Test
    void hello() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/processes/hello");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertEquals("Hello, World", result.getResponse().getContentAsString());
    }

    // FOR DELETE (POST FIRST) -> (THEN DELETE)
}