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

    //添加用户
    @PostMapping("/new")
    public Map postUser(@RequestBody User user){
        userService.addUser(user);
        return Map.of("user",user);
    }
    //删除用户
    @PostMapping("/remove")
    public Map removeUser(@RequestBody User u){
        userService.deleteUser(u);
        return Map.of("user",u);
    }

    //更新用户密码
    @PostMapping("/reset/pass")
    public Map resetPass(@RequestBody User user){
        int u = userService.resetUserPass(user);
        return Map.of("user",u);
    }
    @GetMapping("/users")
    public Map getNonAdminUsers(){
        List<User> list = userService.listNonAdminUsers();
        return Map.of("users",list);
    }

    @GetMapping("/adminusers")
    public Map getAdminUsers(){
        List<User> list = userService.listAdminUsers();
        return Map.of("users",list);
    }
    //发表回复
    @PostMapping("/task/{taskid}/reply")
    public Map postReply(@PathVariable int taskid, @RequestBody Reply reply, @RequestAttribute int uid){
        log.debug("{}",reply.getComment());
        TaskDetail detail = taskDetailService.addReply(taskid,uid,reply);
        return Map.of("taskDetail",detail);
    }
}
