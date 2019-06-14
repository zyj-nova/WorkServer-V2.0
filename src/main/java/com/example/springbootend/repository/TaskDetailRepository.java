package com.example.springbootend.repository;

import com.example.springbootend.entity.Task;
import com.example.springbootend.entity.TaskDetail;
import com.example.springbootend.entity.User;
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

    //获取指定任务的完成人数
    @Query("select count(td.task.id) from TaskDetail td where td.result='完成' and td.task.id =:tid group by td.user.id")
    int queryCompleteNum(@Param("tid") int tid);

    //获取任务名称
    @Query("select detail.task from TaskDetail detail")
    List<Task> queryTask();

    //获取所有任务详情
    @Query("select td from TaskDetail td")
    List<TaskDetail> queryAllDetail();

    //获取所有用户
    @Query("select td.user from TaskDetail td")
    List<User> queryAllUsers();
}
