package com.myTaskManager.taskmanager.taskservice;

import com.myTaskManager.taskmanager.model.Priority;
import com.myTaskManager.taskmanager.model.TaskProcess;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskManagerService {

    TaskProcess getProcessByPID(String pid);

    void addProcess(TaskProcess taskProcess);

    List<TaskProcess> allProcesses(String orderBy);

    void killProcess(String pid);

    void killProcessGroup(String priority);

    void killAll();

}
