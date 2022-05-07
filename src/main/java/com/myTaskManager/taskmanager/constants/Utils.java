package com.myTaskManager.taskmanager.constants;

import com.myTaskManager.taskmanager.model.TaskProcess;

public class Utils {
    public static final Integer MAXIMUM_CAPACITY = 3;

    public static boolean validateModel(TaskProcess taskProcess){
        if(taskProcess.getPriority() == null || taskProcess.getPid() != null || taskProcess.getCreationTime() != null){
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
