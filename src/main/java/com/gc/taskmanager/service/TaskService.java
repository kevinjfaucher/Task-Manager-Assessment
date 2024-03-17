package com.gc.taskmanager.service;

import com.gc.taskmanager.model.Task;
import com.gc.taskmanager.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void save(Task task) {
        taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleComplete(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        task.setComplete(!task.isComplete());
        taskRepository.save(task);
    }

    public List<Task> searchByDescription(String description) {
        return taskRepository.findByDescriptionContainingIgnoreCase(description);
    }

    public List<Task> findAllSortedByDueDateAsc() {
        return taskRepository.findAllByOrderByDueDateAsc();
    }

    public List<Task> findAllSortedByDueDateDesc() {
        return taskRepository.findAllByOrderByDueDateDesc();
    }

    public List<Task> findAllCompletedTasksSortedByDueDate() {
        return taskRepository.findByCompleteTrueOrderByDueDateAsc();
    }

    public List<Task> findAllIncompleteTasksSortedByDueDate() {
        return taskRepository.findByCompleteFalseOrderByDueDateAsc();
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }


}
