package com.example.springbootend;

import com.example.springbootend.entity.Exam;
import com.example.springbootend.entity.Lession;
import com.example.springbootend.entity.User;
import com.example.springbootend.repository.ExamRepository;
import com.example.springbootend.service.ExamDetailService;
import com.example.springbootend.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamServiceTest {
    @Autowired
    private ExamService examService;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamDetailService examDetailService;

    @Test
    public void test1() throws Exception{
        Exam exam = new Exam();
        Lession lession = new Lession();
        lession.setId(4);
        lession.setName("高等数学");
        exam.setCourse(lession);
        exam.setNumOfPerson(1);
        exam.setPlace("丹青810");
        exam.setStartTime(LocalDateTime.of(2019,6,25,10,25));
        exam.setDeadTime(LocalDateTime.of(2019,6,25,12,25));
        examService.addExam(exam);
    }

    @Test
    public void test2(){
        List<Exam> exams= examRepository.findByPlace("锦绣楼4楼403");
        System.out.println(exams.get(0).getCourse().getName());
    }

    @Test
    public void test3(){
        List<User> list = examService.getRecommendTeachers();
        System.out.println(list.size());
    }
}
