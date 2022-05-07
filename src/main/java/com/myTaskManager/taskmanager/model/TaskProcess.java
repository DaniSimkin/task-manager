package com.myTaskManager.taskmanager.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
public class TaskProcess implements Comparable<TaskProcess> {

    //@Null
    private Date creationTime;
    //@Null
    private UUID pid;
    //@NotBlank(message = "Priority is mandatory")
    //@NotEmpty(message = "Priority cannot be null")
    private Priority priority;

    public TaskProcess(){
        this.creationTime = new Date();
        this.pid = UUID.randomUUID();
    }

    public TaskProcess(String priority){
        this.priority = Priority.valueOf(priority);
    }

    @Override
    public int compareTo(TaskProcess tp) {
        return Integer.compare(this.priority.ordinal(), tp.priority.ordinal());
    }
}

