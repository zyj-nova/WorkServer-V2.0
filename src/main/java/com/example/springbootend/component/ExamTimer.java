package com.example.springbootend.component;


import com.example.springbootend.entity.Exam;
import com.example.springbootend.entity.ExamDetail;
import com.example.springbootend.entity.User;
import com.example.springbootend.service.ExamDetailService;
import com.example.springbootend.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 定时发送短信，写入文本提示
 */
@Component
@Slf4j
public class ExamTimer {
    @Autowired
    private ExamService examService;

    @Autowired
    private ExamDetailService examDetailService;
    //每天12点运行
    @Scheduled(cron = "0 * * * * ?")
    public void sendMessage(){
        List<Exam> exams = examService.listExams();
        for (Exam exam:exams){
            StringBuilder message = new StringBuilder();
            //System.out.println((exam.getStartTime().toEpochSecond(ZoneOffset.of("+8")) - LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))/3600);
            if("已分配".equals(exam.getStatus())
                    && (exam.getStartTime().toEpochSecond(ZoneOffset.of("+8")) - LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))/3600 <= 24
            && (exam.getStartTime().toEpochSecond(ZoneOffset.of("+8")) - LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8")))/3600 >0){
                List<User> teachers = examDetailService.listExamTeacher(exam);
                System.out.println(teachers.size());
                message.append(teachers.get(0).getName() + ",您于" + exam.getStartTime().toString().replace("T"," ") + "至" + exam.getDeadTime().toString().replace("T"," "));
                message.append("，在" + exam.getPlace()+",有监考任务,考试科目:"+ exam.getCourse().getName()+",请按时到场。");
                message.append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                System.out.println(message.toString());
            }

           // log.debug("{}",message.toString());
        }
    }
}
