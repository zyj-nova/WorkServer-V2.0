package com.example.springbootend.controller;

import com.example.springbootend.entity.*;
import com.example.springbootend.service.TaskDetailService;
import com.example.springbootend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private TaskDetailService taskDetailService;

    @PostMapping("/new")
    public Map postUser(@RequestBody User user){
        userService.addUser(user);
        return Map.of("user",user);
    }

    @GetMapping("/users")
    public Map getNonAdminUsers(){
        List<User> list = userService.listNonAdminUsers();
        return Map.of("users",list);
    }

    @PostMapping("/task/{taskid}/reply")
    public Map postReply(@PathVariable int taskid, @RequestBody Reply reply, @RequestAttribute int uid){
        log.debug("{}",reply.getComment());
        TaskDetail detail = taskDetailService.addReply(taskid,uid,reply);
        return Map.of("taskDetail",detail);
    }
}
