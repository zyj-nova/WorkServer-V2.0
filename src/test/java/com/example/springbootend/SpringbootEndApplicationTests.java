package com.example.springbootend;

import com.example.springbootend.component.EncryptorComponent;
import com.example.springbootend.entity.User;
import com.example.springbootend.repository.UserRepository;
import com.example.springbootend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootEndApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EncryptorComponent encryptorComponent;

    @Autowired
    private UserRepository userRepository;
    @Test
    public void contextLoads() {

    }

    @Test
    public void test(){
        List<User> list = dao.findAllByUserAuthority(3);
        System.out.println(list.size());
    }

    @Test
    public void test2(){
        String password = "123456";
        String code = passwordEncoder.encode(password);
        String code2 = passwordEncoder.encode(password);
       // $2a$10$4glywfDErIIfG/QPuwQ1G.M7jZSCdHXZOWY1wQQrNS9NByo7WN/hu,
        // $2a$10$wbNsnag5x7anTkAWIhJ4vuNQyG7kPTtRwqbR2I9pikvAfNjyCfIee

        log.debug("{},{}",code,code2);
    }

    @Test
    public void generateToken(){
        Map map = Map.of("uid",3,"aid",1);
        String token = encryptorComponent.encrypt(map);
        log.debug("{}",token);
    }

    @Test
    public void test3(){
        User user = new User();
        user.setId(4);
        user.setName("吴镇宇");
        userService.updateUser(user);
    }

}
