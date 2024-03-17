package com.gc.taskmanager.controller;

import com.gc.taskmanager.model.Task;
import com.gc.taskmanager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    /**
     * Handles GET requests on the home ("/") path. It fetches tasks based on request parameters search, sort and status.
     *
     * @param search a string to search tasks by description;
     * @param sort   a string to sort the tasks based on due date;
     * @param status a Boolean to filter tasks by completion status;
     */

    @GetMapping("/")
    public String listTasks(Model model,
                            @RequestParam(value = "search", required = false) String search,
                            @RequestParam(value = "sort", required = false) String sort,
                            @RequestParam(value = "status", required = false) Boolean status) {
        List<Task> tasks;
        if (status != null) {
            tasks = status ? taskService.findAllCompletedTasksSortedByDueDate() : taskService.findAllIncompleteTasksSortedByDueDate();
        } else if (sort != null && !sort.isEmpty()) {
            tasks = "asc".equals(sort) ? taskService.findAllSortedByDueDateAsc() : taskService.findAllSortedByDueDateDesc();
        } else if (search != null && !search.isEmpty()) {
            tasks = taskService.searchByDescription(search);
        } else {
            tasks = taskService.findAllTasks();
        }
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @PostMapping("/add")
    public String addTask(@ModelAttribute Task task) {
        taskService.save(task);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return "redirect:/";
    }

    @GetMapping("/toggleComplete/{id}")
    public String toggleTaskComplete(@PathVariable Long id) {
        taskService.toggleComplete(id);
        return "redirect:/";
    }
}