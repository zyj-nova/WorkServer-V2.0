package com.example.springbootend.controller;


import com.example.springbootend.entity.TaskDetail;
import com.example.springbootend.service.TaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/get/result")
    public Map getAllResult(){
        return Map.of("results",taskDetailService.getResults());
    }


}
