package com.mycrud.trelloapiproject.controller;


import com.mycrud.trelloapiproject.domain.TaskDto;
import com.mycrud.trelloapiproject.mapper.TaskMapper;
import com.mycrud.trelloapiproject.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/task")
public class TaskController {
    @Autowired
    private DbService dbService;
    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getTasks")
    public List<TaskDto> getTasks(){
        return taskMapper.maptoTaskDtoList(dbService.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.GET, value="getTask")
    public TaskDto getTask(){
        return new TaskDto(1l,"Do shopping", "Carrots, milk");
    }

    @RequestMapping(method = RequestMethod.POST, value = "createTask")
    public void createTask(TaskDto taskDto){

    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateTask")
    public TaskDto updateTask(@RequestBody TaskDto taskDto){
        return new TaskDto(1l,"update task", "this is updated task");

    }

    @RequestMapping(method = RequestMethod.DELETE,value="deleteTask")
    public void deleteTask(long taskId){
    }






}
