package com.gc.taskmanager.repository;

import com.gc.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByDescriptionContainingIgnoreCase(String description);

    List<Task> findAllByOrderByDueDateAsc();

    List<Task> findAllByOrderByDueDateDesc();

    List<Task> findByCompleteTrueOrderByDueDateAsc();

    List<Task> findByCompleteFalseOrderByDueDateAsc();

}
