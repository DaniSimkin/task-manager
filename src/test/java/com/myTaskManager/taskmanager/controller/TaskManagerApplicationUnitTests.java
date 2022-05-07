package com.myTaskManager.taskmanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myTaskManager.taskmanager.factory.TaskFactoryImpl;
import com.myTaskManager.taskmanager.model.TaskProcess;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaskManagerApplicationUnitTests {

    @Autowired
    private MockMvc mockMvc;
    /*
    @Test
    public void saveProcessTestSuccess() throws Exception {
        TaskProcess taskProcess = new TaskProcess();
        String taskProcessAsJson = new ObjectMapper().writeValueAsString(taskProcess);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/task-manager/addProcess/high").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(taskProcessAsJson);

        //this.mockMvc.perform(post("/task-manager/addProcess/high").content()
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        int actualResult = mvcResult.getResponse().getStatus();
        int expectedResult = HttpStatus.OK.value();

        assertEquals("Result expected!",expectedResult,actualResult);
    }

    /*

    @Test
    public void getProcessTestSuccess() throws Exception {
        this.mockMvc.perform(post("/task-manager/addProcess/high").content()
    }

    @Test
    public void listProcessesTestSuccess() throws Exception {
        this.mockMvc.perform(post("/task-manager/addProcess/high").content()
    }

    @Test
    public void deleteProcessGroup() throws Exception {
        this.mockMvc.perform(post("/task-manager/addProcess/high").content()
    }

    @Test
    public void deleteAllProcesses() throws Exception {
        this.mockMvc.perform(post("/task-manager/addProcess/high").content()
    }

     */

}
