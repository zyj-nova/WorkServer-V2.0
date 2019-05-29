package com.example.springbootend.repository;

import com.example.springbootend.entity.TaskDetail;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskDetailRepository extends CustomizedRepoistory<TaskDetail,Integer> {
}
