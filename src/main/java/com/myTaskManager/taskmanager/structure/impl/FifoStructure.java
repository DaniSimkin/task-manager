package com.myTaskManager.taskmanager.structure.impl;


import com.google.common.collect.EvictingQueue;
import com.myTaskManager.taskmanager.constants.Utils;
import com.myTaskManager.taskmanager.exception.custom.ProcessNotExistException;
import com.myTaskManager.taskmanager.model.TaskProcess;
import com.myTaskManager.taskmanager.structure.DataStructure;
import org.springframework.stereotype.Component;


import java.util.*;


@Component
public class FifoStructure implements DataStructure {

    private final EvictingQueue<TaskProcess> evictingQueue = EvictingQueue.create(Utils.MAXIMUM_CAPACITY);
    private final Map<UUID, TaskProcess> evictingMap = new HashMap<>();

    public boolean containsUUID(String pid){
        return evictingMap.containsKey(UUID.fromString(pid));
    }

    public TaskProcess getByUid(String pid){
        return evictingMap.get(UUID.fromString(pid));
    }

    public void addProcessToStructure(TaskProcess taskProcess){
        if(evictingQueue.remainingCapacity() == 0){
            TaskProcess removeFromHead =  evictingQueue.peek();
            evictingMap.remove(removeFromHead.getPid());
        }
        evictingQueue.add(taskProcess);
        evictingMap.put(taskProcess.getPid(), taskProcess);
    }

    public List<TaskProcess> getSortedBy(String orderBy){
        List<TaskProcess> sortedList = new ArrayList<TaskProcess>(evictingMap.values());
        if("time".equalsIgnoreCase(orderBy)){
            sortedList.sort(Comparator.comparing(TaskProcess::getCreationTime));
        }
        if("priority".equalsIgnoreCase(orderBy)){
            sortedList.sort(Comparator.comparing(TaskProcess::getPriority));
        }
        if("pid".equalsIgnoreCase(orderBy)){
            sortedList.sort(Comparator.comparing(TaskProcess::getPid));
        }
        return sortedList;
    }

    public void killProcessInStructure(String pid){
        if(!containsUUID(pid)){
            throw new ProcessNotExistException();
        }
        TaskProcess toRemove = getByUid(pid);
        evictingMap.remove(toRemove.getPid());
        evictingQueue.remove(toRemove);
    }

    public void killAllProcesses(){
        evictingQueue.clear();
        evictingMap.clear();
    }

    public void killSpecificProcessGroup(String priority){
        for (Iterator<Map.Entry<UUID,TaskProcess>> it = evictingMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<UUID,TaskProcess> e = it.next();
            if (priority.equalsIgnoreCase(String.valueOf(e.getValue().getPriority()))) {
                it.remove();
                evictingQueue.remove(e.getValue());
            }
        }
    }


}
