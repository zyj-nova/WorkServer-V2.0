package com.example.springbootend;

import com.example.springbootend.component.EncryptorComponent;
import com.example.springbootend.entity.User;
import com.example.springbootend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootEndApplicationTests {

    @Autowired
    private UserRepository dao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EncryptorComponent encryptorComponent;

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
        log.debug("{}",passwordEncoder.matches(password,code));
    }

    @Test
    public void generateToken(){
        Map map = Map.of("uid",3,"aid",1);
        String token = encryptorComponent.encrypt(map);
        log.debug("{}",token);
    }
}
