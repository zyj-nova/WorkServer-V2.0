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
       boolean flag = examDetailService.addExamTeacher(examing);//true表示有冲突
        return Map.of("flag",flag);
    }
//
//    //删除监考详细信息
//    @PostMapping("/delete/examdetail")
//    public Map deleteExamDetail(@RequestBody ExamDetail examDetail){
//        examDetailService.deleteExamDetail(examDetail);
//        return Map.of("examDEtail",examDetail);
//    }

    @GetMapping("/list/my/exam")
    public Map getTeacherExam(@RequestAttribute int uid){
        return Map.of("myexams",examDetailService.listTeacherExam(uid));
    }

    @PostMapping("/get/teachers")
    public Map getExamTeachers(@RequestBody Exam exam){
        return Map.of("teachers",examDetailService.listExamTeacher(exam));
    }

    //删除该场监考的教师
    @PostMapping("/delete/examdetail")
    public Map deleteExamTEachers(@RequestBody Exam exam){
        examDetailService.deleteExamDetail(exam);
    return Map.of();
    }

}
