package com.myTaskManager.taskmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myTaskManager.taskmanager.model.TaskProcess;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskProcessController.class)
public class TaskManagerApplicationIntTests {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    /*

    @Test
    public void saveProcessTestSuccess() throws Exception {
        TaskProcess taskProcess = new TaskProcess();
        mockMvc.perform(post("/task-manager/addProcess/MEDIUM")
                .content(om.writeValueAsString(taskProcess))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

     */
}
