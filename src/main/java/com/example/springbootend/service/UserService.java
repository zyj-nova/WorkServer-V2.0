package com.example.springbootend.service;

import com.example.springbootend.entity.User;
import com.example.springbootend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public User getUser(String number) {
        return userRepository.find(number);
    }

    public User getUserById(int id){
        return userRepository.findById(id);
    }

    //管理员添加用户
    public User addUser(User u){
        String pass = passwordEncoder.encode(u.getPassword());
        u.setPassword(pass);
        userRepository.save(u);
        return userRepository.refresh(u);
    }
    //列出所有非管理员用户的信息
    public List<User> listNonAdminUsers(){
        List<User> list = userRepository.findAllByUserAuthority(1);
        return list;
    }

    //管理员授权普通用户为管理员 /settings/
    public User UpdateUser(int aid,String number){
        userRepository.updateAuthority(aid,number);
        return userRepository.find(number);
    }


}
