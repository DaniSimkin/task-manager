package com.myTaskManager.taskmanager.taskservice.impl;

import com.myTaskManager.taskmanager.exception.custom.ProcessNotExistException;
import com.myTaskManager.taskmanager.model.TaskProcess;
import com.myTaskManager.taskmanager.structure.impl.NaiveStructure;
import com.myTaskManager.taskmanager.taskservice.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NaiveTaskManagerServiceImpl implements TaskManagerService {

    @Autowired
    private NaiveStructure naiveStructure;

    @Override
    public TaskProcess getProcessByPID(String pid){
        TaskProcess tp = naiveStructure.getByUid(pid);
        if(tp == null) {
            throw new ProcessNotExistException();
        }
        return tp;
    }

    @Override
    public void addProcess(TaskProcess taskProcess) {
        naiveStructure.addProcessToStructure(taskProcess);
    }

    @Override
    public List<TaskProcess> allProcesses(String orderBy) {
       return naiveStructure.getSortedBy(orderBy);
    }

    @Override
    public void killProcess(String pid){
        naiveStructure.killProcessInStructure(pid);
    }

    @Override
    public void killProcessGroup(String priority) {
        naiveStructure.killSpecificProcessGroup(priority);
    }

    @Override
    public void killAll() {
        naiveStructure.killAllProcesses();
    }
}
