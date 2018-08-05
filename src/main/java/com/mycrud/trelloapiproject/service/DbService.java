package com.mycrud.trelloapiproject.service;

import com.mycrud.trelloapiproject.domain.Task;
import com.mycrud.trelloapiproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

//    public Task getTask(){
//        return repository.findById();
//    }
}
