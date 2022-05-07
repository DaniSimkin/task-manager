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
public class PriorityStructure implements DataStructure {

    private final PriorityQueue<TaskProcess> priorityQueue = new PriorityQueue(Utils.MAXIMUM_CAPACITY);

    @Override
    public boolean containsUUID(String pid) {
        for (Iterator<TaskProcess> it = priorityQueue.iterator(); it.hasNext();) {
            if (it.next().getPid().equals(UUID.fromString(pid))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public TaskProcess getByUid(String pid) {
        for (Iterator<TaskProcess> it = priorityQueue.iterator(); it.hasNext();) {
            TaskProcess current = it.next();
            if (current.getPid().equals(UUID.fromString(pid))) {
                return current;
            }
        }
        return null;
    }

    @Override
    public void addProcessToStructure(TaskProcess taskProcess) {
        if(priorityQueue.size() >= Utils.MAXIMUM_CAPACITY) {
            List<TaskProcess> lowerPriorities = priorityQueue.stream().filter
                    (task -> task.getPriority().ordinal() < taskProcess.getPriority().ordinal()).collect(Collectors.toList());
            if (lowerPriorities.isEmpty() || lowerPriorities == null) {
                throw new UnableToAddProcessException();
            }
            TaskProcess taskToRemove = lowestOldestPriority(lowerPriorities);
            priorityQueue.remove(taskToRemove);
            priorityQueue.add(taskProcess);
        }
        if(priorityQueue.size() < Utils.MAXIMUM_CAPACITY){
            priorityQueue.add(taskProcess);
        }


    }

    @Override
    public List<TaskProcess> getSortedBy(String orderBy) {
        List<TaskProcess> sortedList = new ArrayList<TaskProcess>(priorityQueue.stream().toList());
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

    @Override
    public void killProcessInStructure(String pid) {
        if(!containsUUID(pid)){
            throw new ProcessNotExistException();
        }
        for (Iterator<TaskProcess> it = priorityQueue.iterator(); it.hasNext();) {
            TaskProcess current = it.next();
            if (UUID.fromString(pid).equals(current.getPid())){
                it.remove();
            }
        }

    }

    @Override
    public void killAllProcesses() {
        priorityQueue.clear();
    }

    @Override
    public void killSpecificProcessGroup(String priority) {
        priorityQueue.removeIf(current -> priority.equalsIgnoreCase(String.valueOf(current.getPriority())));
    }

    private TaskProcess lowestOldestPriority(List<TaskProcess> lowerPriorities){
        TaskProcess minElement = lowerPriorities.get(0);
        for(int i=1; i < lowerPriorities.size(); i++){
            if((minElement.getCreationTime().getTime() > lowerPriorities.get(i).getCreationTime().getTime())
                    && (minElement.getPriority().ordinal() == lowerPriorities.get(i).getPriority().ordinal())){
                minElement = lowerPriorities.get(i);
            }
            if(minElement.getPriority().ordinal() != lowerPriorities.get(i).getPriority().ordinal()){
                return minElement;
            }
        }
        return minElement;
    }

}
