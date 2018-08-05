package com.mycrud.trelloapiproject.mapper;


import com.mycrud.trelloapiproject.domain.Task;
import com.mycrud.trelloapiproject.domain.TaskDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public Task mapToTask(TaskDto taskDto){
        return new Task(taskDto.getId(),
                        taskDto.getTitle(),
                        taskDto.getContent());
    }

    public TaskDto mapToTaskDto(Task task){
        return new TaskDto(task.getId(),
                            task.getTitle(),
                            task.getContent());
    }

    public List<TaskDto> maptoTaskDtoList (final List<Task> taskList){
        return taskList.stream()
                .map(task -> new TaskDto(task.getId(),task.getTitle(),task.getContent()))
                .collect(Collectors.toList());
    }
}
