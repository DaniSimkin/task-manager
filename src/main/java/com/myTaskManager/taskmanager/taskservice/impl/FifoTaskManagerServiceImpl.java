package com.myTaskManager.taskmanager.taskservice.impl;

import com.myTaskManager.taskmanager.exception.custom.ProcessNotExistException;
import com.myTaskManager.taskmanager.model.TaskProcess;
import com.myTaskManager.taskmanager.structure.impl.FifoStructure;
import com.myTaskManager.taskmanager.taskservice.TaskManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FifoTaskManagerServiceImpl implements TaskManagerService {

    @Autowired
    private FifoStructure fifoStructure;


    @Override
    public TaskProcess getProcessByPID(String pid) {
        TaskProcess tp = fifoStructure.getByUid(pid);
        if(tp == null) {
            throw new ProcessNotExistException();
        }
        return tp;
    }

    @Override
    public void addProcess(TaskProcess taskProcess) {
        fifoStructure.addProcessToStructure(taskProcess);
    }

    @Override
    public List<TaskProcess> allProcesses(String orderBy) {
        return fifoStructure.getSortedBy(orderBy);
    }

    @Override
    public void killProcess(String pid) {
        fifoStructure.killProcessInStructure(pid);
    }

    @Override
    public void killProcessGroup(String priority) {
        fifoStructure.killSpecificProcessGroup(priority);
    }

    @Override
    public void killAll() {
        fifoStructure.killAllProcesses();
    }
}
