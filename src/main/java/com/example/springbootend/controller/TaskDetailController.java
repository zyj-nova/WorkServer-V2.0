package com.example.springbootend.controller;


import com.example.springbootend.service.TaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/taskdetail")
public class TaskDetailController {
    @Autowired
    private TaskDetailService taskDetailService;

    @GetMapping("/get/{id}")
    public Map getTaskDetail(@PathVariable int id){
        List tasks = taskDetailService.getAllReply(id);
        return Map.of("tasks",tasks);
    }
}
