package com.example.springbootend.component;


import com.example.springbootend.entity.Exam;
import com.example.springbootend.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时发送短信，写入文本提示
 */
@Component
@Slf4j
public class ExamTimer {
    @Autowired
    private ExamService examService;
    //每天12点运行
    @Scheduled(cron = "0 0 12 * * ?")
    public void sendMessage(){
        List<Exam> exams = examService.listExams();
        for (Exam exam:exams){
            StringBuilder message = new StringBuilder();

        }
    }
}
