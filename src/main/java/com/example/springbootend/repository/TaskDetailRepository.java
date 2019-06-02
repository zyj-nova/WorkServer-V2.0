package com.example.springbootend.repository;

import com.example.springbootend.entity.Reply;
import com.example.springbootend.entity.TaskDetail;
import com.example.springbootend.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDetailRepository extends CustomizedRepoistory<TaskDetail,Integer> {

    @Query("select td.user from TaskDetail td where td.task.id =:id")
    List<User> queryReplyUSer(@Param("id") int id);

    @Query("select td from TaskDetail td where td.task.id = :id")
    List<TaskDetail> queryReplyContent(@Param("id") int id);
}
