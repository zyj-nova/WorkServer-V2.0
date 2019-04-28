package com.example.springbootend.repository;

import com.example.springbootend.entity.Homework;
import com.example.springbootend.entity.HomeworkDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkDetailRepository extends CustomizedRepoistory<HomeworkDetail, Integer> {
}
