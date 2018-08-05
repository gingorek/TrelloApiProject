package com.mycrud.trelloapiproject.repository;

import com.mycrud.trelloapiproject.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task,Long> {

    @Override
    List<Task> findAll();

//    @Override
//    Task findById();
}
