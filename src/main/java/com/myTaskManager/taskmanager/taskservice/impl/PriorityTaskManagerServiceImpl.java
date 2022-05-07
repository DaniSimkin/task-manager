package com.myTaskManager.taskmanager.taskservice.impl;

import com.myTaskManager.taskmanager.exception.custom.ProcessNotExistException;
import com.myTaskManager.taskmanager.model.TaskProcess;
import com.myTaskManager.taskmanager.structure.impl.PriorityStructure;
import com.myTaskManager.taskmanager.taskservice.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriorityTaskManagerServiceImpl implements TaskManagerService {

    @Autowired
    PriorityStructure priorityStructure;

    @Override
    public TaskProcess getProcessByPID(String pid) {
        TaskProcess tp = priorityStructure.getByUid(pid);
        if(tp == null) {
            throw new ProcessNotExistException();
        }
        return tp;
    }

    @Override
    public void addProcess(TaskProcess taskProcess) {
        priorityStructure.addProcessToStructure(taskProcess);
    }

    @Override
    public List<TaskProcess> allProcesses(String orderBy) {
        return priorityStructure.getSortedBy(orderBy);
    }

    @Override
    public void killProcess(String pid) {
        priorityStructure.killProcessInStructure(pid);
    }

    @Override
    public void killProcessGroup(String priority) {
        priorityStructure.killSpecificProcessGroup(priority);
    }

    @Override
    public void killAll() {
        priorityStructure.killAllProcesses();
    }
}
