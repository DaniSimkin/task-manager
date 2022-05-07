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

    public boolean containsUUID(String pid){
        for (Iterator<TaskProcess> it = evictingQueue.iterator(); it.hasNext();) {
            if (it.next().getPid().equals(UUID.fromString(pid))) {
                return true;
            }
        }
        return false;
    }

    public TaskProcess getByUid(String pid){
        for (Iterator<TaskProcess> it = evictingQueue.iterator(); it.hasNext();) {
            TaskProcess current = it.next();
            if (current.getPid().equals(UUID.fromString(pid))) {
                return current;
            }
        }
        return null;
    }

    public void addProcessToStructure(TaskProcess taskProcess){
        evictingQueue.add(taskProcess);
    }

    public List<TaskProcess> getSortedBy(String orderBy){
        List<TaskProcess> sortedList = new ArrayList<>(evictingQueue.stream().toList());
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
        for (Iterator<TaskProcess> it = evictingQueue.iterator(); it.hasNext();) {
            TaskProcess current = it.next();
            if (UUID.fromString(pid).equals(current.getPid())){
                it.remove();
            }
        }
    }

    public void killAllProcesses(){
        evictingQueue.clear();
    }

    public void killSpecificProcessGroup(String priority){
        evictingQueue.removeIf(current -> priority.equalsIgnoreCase(String.valueOf(current.getPriority())));
    }


}
