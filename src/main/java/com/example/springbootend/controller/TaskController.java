package com.example.springbootend.controller;


import com.example.springbootend.entity.Task;
import com.example.springbootend.repository.TaskRepository;
import com.example.springbootend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.rmi.MarshalledObject;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @PostMapping("/add")
    public Map postTask(@RequestBody Task task){
        Task t = taskService.addTask(task);
        return Map.of("task",t);
    }

    @GetMapping("/list")
    public Map getAllTasks(){
        List<Task> tasks = taskService.listAllTasks();
        return Map.of("tasks",tasks);
    }

    @PostMapping("/close")
    public Map closeTask(@RequestBody Task t){
        int  p = taskService.updateTaskStatus(t);
        return Map.of("task",p);
    }

    @PostMapping("/update")
    public Map updateTask(@RequestBody Task t){
        Task task = taskService.updateTask(t);
        return Map.of("task",task);
    }

}
