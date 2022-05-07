package com.myTaskManager.taskmanager.structure.impl;

import com.myTaskManager.taskmanager.constants.Utils;
import com.myTaskManager.taskmanager.exception.custom.MaxCapacityException;
import com.myTaskManager.taskmanager.exception.custom.ProcessNotExistException;
import com.myTaskManager.taskmanager.model.TaskProcess;
import com.myTaskManager.taskmanager.structure.DataStructure;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class NaiveStructure implements DataStructure {

    private final Map<UUID, TaskProcess> naiveMap = new HashMap<>();

    public boolean containsUUID(String pid){
      return naiveMap.containsKey(UUID.fromString(pid));
    }

    public TaskProcess getByUid(String pid){
        return naiveMap.get(UUID.fromString(pid));
    }

    public void addProcessToStructure(TaskProcess taskProcess){
        if(naiveMap.size() >= Utils.MAXIMUM_CAPACITY){
            throw new MaxCapacityException();
        }
        naiveMap.put(taskProcess.getPid(), taskProcess);
    }

    public List<TaskProcess> getSortedBy(String orderBy){
        List<TaskProcess> sortedList = new ArrayList<TaskProcess>(naiveMap.values());
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

    public void killProcessInStructure(String pid){
        if(!containsUUID(pid)){
            throw new ProcessNotExistException();
        }
        naiveMap.remove(UUID.fromString(pid));
    }

    public void killAllProcesses(){
        naiveMap.clear();
    }

    public void killSpecificProcessGroup(String priority){
        //hmap.values().removeAll(Collections.singleton("Two")); Will remove only values but keys will remain null
        for (Iterator<Map.Entry<UUID,TaskProcess>> it = naiveMap.entrySet().iterator(); it.hasNext();) {
            Map.Entry<UUID,TaskProcess> e = it.next();
            if (priority.equalsIgnoreCase(String.valueOf(e.getValue().getPriority()))) {
                it.remove();
            }
        }
    }

}
