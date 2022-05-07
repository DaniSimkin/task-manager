package com.myTaskManager.taskmanager.constants;

import com.myTaskManager.taskmanager.model.TaskProcess;

public class Utils {
    public static final Integer MAXIMUM_CAPACITY = 5;

    public static boolean validateJson(String taskProcess){
        if(!taskProcess.contains("priority") || taskProcess.contains("pid") || taskProcess.contains("creationTime")){
            return false;
        }
        return true;
    }

    public static boolean validatePriorityUrlString(String priority){
        if (!"low".equalsIgnoreCase(priority) && !"medium".equalsIgnoreCase(priority) && !"high".equalsIgnoreCase(priority)){
            return false;
        }
        return true;
    }
}
