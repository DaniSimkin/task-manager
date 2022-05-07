package com.myTaskManager.taskmanager.structure.impl;

import com.myTaskManager.taskmanager.constants.Utils;
import com.myTaskManager.taskmanager.exception.custom.ProcessNotExistException;
import com.myTaskManager.taskmanager.exception.custom.UnableToAddProcessException;
import com.myTaskManager.taskmanager.model.TaskProcess;
import com.myTaskManager.taskmanager.structure.DataStructure;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class PriorityQueueStructure implements DataStructure {

    //add pol in O(logn)
    private final PriorityQueue<TaskProcess> priorityQueue = new PriorityQueue(Utils.MAXIMUM_CAPACITY);
    private final Map<UUID, TaskProcess> priorityMap = new HashMap<>();

    @Override
    public boolean containsUUID(String pid) {
        return priorityMap.containsKey(UUID.fromString(pid));
    }

    @Override
    public TaskProcess getByUid(String pid) {
        return priorityMap.get(UUID.fromString(pid));
    }

    @Override
    public void addProcessToStructure(TaskProcess taskProcess) {
        if(priorityQueue.size() >= Utils.MAXIMUM_CAPACITY){
            priorityMap.put(taskProcess.getPid(), taskProcess);
            List<TaskProcess> lowerPriorities = priorityQueue.stream().filter
                    (task -> task.getPriority().ordinal() < taskProcess.getPriority().ordinal()).collect(Collectors.toList());
            if(lowerPriorities.isEmpty() || lowerPriorities == null){
                throw new UnableToAddProcessException();
            }

           // List<TaskProcess> lowestPriorities = lowerPriorities.stream().filter(task -> task.getPriority().ordinal() )
            TaskProcess minCreationTimeProcess = null;
            for(TaskProcess tp: lowerPriorities){
                minCreationTimeProcess=(tp.getCreationTime().getTime() < minCreationTimeProcess.getCreationTime().getTime())?tp:minCreationTimeProcess;
            }
            System.out.println("MIN Creation Time: " + minCreationTimeProcess.getCreationTime());
            priorityQueue.remove(minCreationTimeProcess);
            priorityQueue.add(taskProcess);
            priorityMap.remove(minCreationTimeProcess.getPid());
            priorityMap.put(taskProcess.getPid(), taskProcess);
        }
        else if(priorityQueue.size() < Utils.MAXIMUM_CAPACITY){
            priorityQueue.add(taskProcess);
            priorityMap.put(taskProcess.getPid(), taskProcess);
        }

    }

    @Override
    public List<TaskProcess> getSortedBy(String orderBy) {
        List<TaskProcess> sortedList = new ArrayList<TaskProcess>(priorityMap.values());
        if("time".equalsIgnoreCase(orderBy)){
            sortedList.sort(Comparator.comparing(TaskProcess::getCreationTime)); //tp -> tp.getCreationTime()
        }
        if("priority".equalsIgnoreCase(orderBy)){
            sortedList.sort(Comparator.comparing(TaskProcess::getPriority));
        }
        if("pid".equalsIgnoreCase(orderBy)){
            sortedList.sort(Comparator.comparing(TaskProcess::getPid));
        }
        return sortedList;
    }

    @Override
    public void killProcessInStructure(String pid) {
        if(!containsUUID(pid)){
            throw new ProcessNotExistException();
        }
        TaskProcess toRemove = getByUid(pid);
        priorityQueue.remove(toRemove);
        priorityMap.remove(toRemove.getPid());
    }

    @Override
    public void killAllProcesses() {
        priorityQueue.clear();
        priorityMap.clear();
    }

    @Override
    public void killSpecificProcessGroup(String priority) {
        for (Iterator<Map.Entry<UUID,TaskProcess>> it = priorityMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<UUID,TaskProcess> e = it.next();
            if (priority.equalsIgnoreCase(String.valueOf(e.getValue().getPriority()))) {
                it.remove();
                priorityQueue.remove(e.getValue());
            }
        }
    }

}
