package com.example.springbootend.controller;


import com.example.springbootend.entity.User;
import com.example.springbootend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    //列出登录用户的个人信息
    @GetMapping("/user")//向此地址请求
    public Map getMyInfo(@RequestAttribute int uid){//请求包含的属性
        return Map.of("user",userService.getUserById(uid));//生成user返回给前端
    }

    //授权普通用户为管理员
    @PostMapping("/set/user/authority")//向此地址传递json数据
     public Map updateAuthority(@RequestBody User user){//将请求的json数据转化为java对象
        User u = userService.UpdateUserAuthority(2,user.getId());//
        return Map.of("user",u);//返回一个json对象
     }
    //列出所有普通用户
     @GetMapping("/get/users")//请求 返回一个列表
    public Map getUsers(){
        List<User> list = userService.listNonAdminUsers();//将普通用户分装到列表里
        return Map.of("users",list);//将列表返回给前端
    }
    //更新用户个人信息
    @PostMapping("/set/update/user")
    public Map postUser(@RequestBody User user){
        User u = userService.updateUser(user);
        return Map.of("user",u);
    }
    //验证密码
    @PostMapping("/get/pass")
    public Map validOldPass(@RequestBody User user){
        boolean flag = userService.validPass(user);
        return Map.of("flag",flag);
    }
    //超级管理员取消授权管理员
    @PostMapping("/set/user/cancel")//向此地址传递一个json数据
    public Map updateNoAuthority(@RequestBody User user){//更新user
        User u = userService.UpdateUserAuthority(1,user.getId());//根据用户的id更新数据
        return Map.of("user",u);//将user返回给前端
}

}
