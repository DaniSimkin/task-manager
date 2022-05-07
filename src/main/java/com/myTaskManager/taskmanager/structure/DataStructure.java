package com.myTaskManager.taskmanager.structure;

import com.myTaskManager.taskmanager.model.TaskProcess;

import java.util.List;

public interface DataStructure {

     boolean containsUUID(String pid);

     TaskProcess getByUid(String pid);

     void addProcessToStructure(TaskProcess taskProcess);

     List<TaskProcess> getSortedBy(String orderBy);

     void killProcessInStructure(String pid);

     void killAllProcesses();

     void killSpecificProcessGroup(String priority);

}
