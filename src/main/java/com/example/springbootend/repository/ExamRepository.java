package com.example.springbootend.repository;

import com.example.springbootend.entity.Exam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExamRepository extends CustomizedRepoistory<Exam,Integer> {

    //查询单条监考信息
    @Query("select e from Exam e where e.id =:id")
    Exam list(@Param("id") int id);

    @Query("SELECT exam FROM Exam exam WHERE exam.place=:place")
    List<Exam> findByPlace(@Param("place") String place);


}
