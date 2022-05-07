package com.myTaskManager.taskmanager.controller;

import com.myTaskManager.taskmanager.factory.TaskFactoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TaskManagerApplicationUnitTests {

    @Autowired
    private MockMvc mockMvc;
    /*
    @Test
    public void saveProcessTestSuccess() throws Exception {
        this.mockMvc.perform(post("/task-manager/addProcess/high").content()
    }

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
