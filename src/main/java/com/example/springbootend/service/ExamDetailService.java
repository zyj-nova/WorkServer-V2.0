package com.example.springbootend.service;


import com.example.springbootend.entity.Exam;
import com.example.springbootend.entity.ExamDetail;
import com.example.springbootend.entity.User;
import com.example.springbootend.repository.ExamDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ExamDetailService {
    @Autowired
    private ExamDetailRepository examDetailRepository;

    public ExamDetail addExamDetail(ExamDetail detail){
        examDetailRepository.save(detail);
        return examDetailRepository.refresh(detail);
    }
    //获取某一场监考的监考老师
    public List<User> listExamTeacher(Exam exam){
        return examDetailRepository.findExamTeacher(exam.getId());
    }
}
