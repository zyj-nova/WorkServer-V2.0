package com.example.springbootend.controller;

import com.example.springbootend.entity.Exam;
import com.example.springbootend.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    @PostMapping("/admin/add")
    public Map postExam(@RequestBody Exam exam){
        try{
            Exam e = examService.addExam(exam);
            return Map.of("exam",e);
        }catch (Exception e) {
            throw  new RuntimeException();
        }
    }

    @GetMapping("/admin/list")
    public Map listExams(){
        return Map.of("exams",examService.listExams());
    }

    @PostMapping("/admin/delete")
    public Map deleteExam(@RequestBody Exam exam){
        examService.deleteExam(exam);
        return Map.of();
    }

}
