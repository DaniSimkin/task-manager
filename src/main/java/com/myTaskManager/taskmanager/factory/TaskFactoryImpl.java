package com.myTaskManager.taskmanager.factory;

import com.myTaskManager.taskmanager.constants.TaskProcessApproachConstants;
import com.myTaskManager.taskmanager.taskservice.impl.FifoTaskManagerServiceImpl;
import com.myTaskManager.taskmanager.taskservice.impl.NaiveTaskManagerServiceImpl;
import com.myTaskManager.taskmanager.taskservice.impl.PriorityTaskManagerServiceImpl;
import com.myTaskManager.taskmanager.taskservice.TaskManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskFactoryImpl {

    private final NaiveTaskManagerServiceImpl naiveTaskManagerService;
    private final FifoTaskManagerServiceImpl fifoTaskManagerService;
    private final PriorityTaskManagerServiceImpl priorityTaskManagerService;

    private static final Map<String, TaskManagerService> handler = new HashMap<>();

    @PostConstruct
    private Map<String, TaskManagerService> serviceHandler() {
        handler.put(TaskProcessApproachConstants.NAIVE, naiveTaskManagerService);
        handler.put(TaskProcessApproachConstants.FIFO, fifoTaskManagerService);
        handler.put(TaskProcessApproachConstants.PRIORITY, priorityTaskManagerService);
        return handler;
    }

    public static TaskManagerService getInstance(String approach) {
        return Optional.ofNullable(handler.get(approach)).orElseThrow(() -> new IllegalArgumentException("Invalid approach"));
    }

}
