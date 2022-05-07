package com.myTaskManager.taskmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myTaskManager.taskmanager.constants.Utils;
import com.myTaskManager.taskmanager.factory.TaskFactoryImpl;
import com.myTaskManager.taskmanager.model.TaskProcess;
import com.myTaskManager.taskmanager.taskservice.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/task-manager")
@RestController
public class TaskProcessController {

    private TaskManagerService taskManagerService;

    @Autowired
    private TaskFactoryImpl taskFactory;

    @Autowired
    private ObjectMapper objectMapper;


    @GetMapping("/getProcess/{approach}/{pid}")
    public TaskProcess getProcess(@PathVariable ("approach") String approach, @PathVariable("pid") String pid) {
        taskManagerService = taskFactory.getInstance(approach);
        return taskManagerService.getProcessByPID(pid);
    }

    @PostMapping("/addProcess/{approach}")
    public ResponseEntity<String> saveProcess(@PathVariable ("approach") String approach, @RequestBody String taskProcess) throws JsonProcessingException {
        /*
        System.out.println("pid: " + taskProcess.getPid() + " Time: " + taskProcess.getCreationTime() + " Priority: " + taskProcess.getPriority());
        if(!Utils.validateModel(taskProcess)){
            throw new IllegalArgumentException("Only Priority Is Allowed");
        }
         */
        if(!Utils.validateJson(taskProcess)) {
            return ResponseEntity.badRequest().body("Only Priority Is Allowed");
        }
        try{
           TaskProcess tp = objectMapper.readValue(taskProcess, TaskProcess.class);
           taskManagerService = taskFactory.getInstance(approach);
           taskManagerService.addProcess(tp);
           return ResponseEntity.ok().body(objectMapper.writeValueAsString(tp));
        }
        catch (JsonProcessingException e){
            return ResponseEntity.badRequest().body("Only Priority Is Allowed");
        }
    }

    @GetMapping("/listAll/{approach}/{order-type}")
    public List<TaskProcess> listProcesses(@PathVariable ("approach") String approach, @PathVariable("order-type") String orderBy) {
        taskManagerService = taskFactory.getInstance(approach);
        return taskManagerService.allProcesses(orderBy);
    }

    @DeleteMapping("/deleteProcess/{approach}/{pid}")
    public void deleteProcess(@PathVariable ("approach") String approach, @PathVariable("pid") String pid)  {
        taskManagerService = taskFactory.getInstance(approach);
        taskManagerService.killProcess(pid);
    }

    @DeleteMapping("/deleteGroup/{approach}/{priority}")
    public void deleteProcessGroup(@PathVariable ("approach") String approach, @PathVariable("priority") String priority) {
        if(!Utils.validatePriorityUrlString(priority)){
             throw new RuntimeException("Priority Name In Request is Incorrect");
        }
        taskManagerService = taskFactory.getInstance(approach);
        taskManagerService.killProcessGroup(priority);
    }

    @DeleteMapping("/killAll/{approach}")
    public void deleteAllProcesses(@PathVariable ("approach") String approach){
        taskManagerService = taskFactory.getInstance(approach);
        taskManagerService.killAll();
    }


}


