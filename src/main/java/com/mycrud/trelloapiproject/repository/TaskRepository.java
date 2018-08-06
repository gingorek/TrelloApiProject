package com.mycrud.trelloapiproject.repository;

import com.mycrud.trelloapiproject.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Tak naprawdę nie musimy nadpisywać tych metod, jednak później - gdy będziemy chcieli z nich skorzystać
 * - będziemy musieli dokonywać rzutowania:  * (List<Task>)findAll(),
 * ponieważ typem zwracanym przez metodę findAll() jest typ Iterable, a nam wygodniej będzie się pracowało z typem List.
 */

public interface TaskRepository extends CrudRepository<Task,Long> {
    //GET ALL
    @Override
    List<Task> findAll();

    //GET ONE
    Optional<Task> findTaskById(Long taskId);

    //POST
    @Override
    Task save(Task task);

    //DELETE
    void delete(Long taskId);

}
