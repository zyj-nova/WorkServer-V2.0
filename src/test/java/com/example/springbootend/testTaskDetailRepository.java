package com.example.springbootend;

import com.example.springbootend.entity.*;
import com.example.springbootend.repository.LessionRepository;
import com.example.springbootend.repository.TaskDetailRepository;
import com.example.springbootend.service.LessionService;
import com.example.springbootend.service.TaskDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.ls.LSException;

import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class testTaskDetailRepository {
    @Autowired
    private TaskDetailRepository dao;

    @Autowired
    private TaskDetailService service;


    @Test
    @Transactional
    public void testquery(){
        int n = dao.queryCompleteNum(7);
        log.debug("{}",n);
    }

    @Test
    public void test4(){

    }

}
