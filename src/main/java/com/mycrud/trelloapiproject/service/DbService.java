package com.mycrud.trelloapiproject.service;

import com.mycrud.trelloapiproject.domain.Task;
import com.mycrud.trelloapiproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    //Uzywam tu Optional ponieważ jak nie bedzie obiektu o podanym taskId to wtedy wypluwam TaskNotFoundException
    public Optional<Task> getTask(Long taskId){
        return repository.findTaskById(taskId);
    }

    public Task saveTask(Task task){
        return repository.save(task);
    }

    public void deleteTask(Long taskId){
        repository.delete(taskId);
    }
}
