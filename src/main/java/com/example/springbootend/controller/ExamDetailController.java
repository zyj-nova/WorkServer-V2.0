package com.example.springbootend.controller;


import com.example.springbootend.entity.Exam;
import com.example.springbootend.entity.ExamDetail;
import com.example.springbootend.entity.Examing;
import com.example.springbootend.entity.User;
import com.example.springbootend.service.ExamDetailService;
import com.example.springbootend.service.ExamService;
import com.example.springbootend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/exam")
public class ExamDetailController {

    @Autowired
    private ExamDetailService examDetailService;

    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    //获取推荐的教师
    @GetMapping("/list/teachers")
    public Map getRecommendTeachers(){
        return Map.of("teachers",examService.getRecommendTeachers());
    }

    //分配指定监考的监考人员
    @PostMapping("/update/add/teacher")
    public Map postExamTeachers(@RequestBody Examing examing){
        List<Integer> ids = examing.getTids();
        Exam exam = examing.getExam();
        System.out.println(ids.size());
        for(Integer u:ids){
            User user = userService.getUserById(u);
            if (examService.getExamTimesByTeacher(user) >= 2){
                throw new RuntimeException();
            }
            ExamDetail detail = new ExamDetail();
            detail.setExam(exam);
            detail.setTeacher(user);
            examDetailService.addExamDetail(detail);
        }
        return Map.of();
    }

}
