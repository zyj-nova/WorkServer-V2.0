package com.example.springbootend.service;

import com.example.springbootend.entity.User;
import com.example.springbootend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Service
@Transactional
public class UserService {
    @PersistenceContext
    private EntityManager em;
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
    public User UpdateUserAuthority(int aid, int uid){
        User u = em.find(User.class,uid);
        log.debug("{}",u.getNumber());
        u.setAuthority(aid);
        return u;
    }

    //更新用户个人信息,不包括密码
    public User updateUser(User u){
       User user = em.find(User.class,u.getId());
       user.setIntroduction(u.getIntroduction());
       user.setPhone(u.getPhone());
       user.setTitle(u.getTitle());
       user.setAuthority(u.getAuthority());
       user.setName(u.getName());
       user.setNumber(u.getNumber());
       return user;
    }


}
