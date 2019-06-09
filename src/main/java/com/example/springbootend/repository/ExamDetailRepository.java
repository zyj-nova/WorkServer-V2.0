package com.example.springbootend.repository;

import com.example.springbootend.entity.Exam;
import com.example.springbootend.entity.ExamDetail;
import com.example.springbootend.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExamDetailRepository extends CustomizedRepoistory<ExamDetail,Integer>{

    //据特定监考的id找到相关老师
    @Query("select detail.teacher from ExamDetail detail where detail.exam.id = :id")
    List<User> findExamTeacher(@Param("id") int id);

    // 找到指定老师所有考试
    @Query("select detail.exam from ExamDetail detail where detail.teacher.id = :tid")
    List<Exam> findByTeacher(@Param("tid") int tid);

    @Query("select count(detail.id) from ExamDetail detail where detail.teacher.id= :tid group by detail.teacher.id")
    int times(@Param("tid") int tid);
}
