package com.example.springbootend.repository;

import com.example.springbootend.entity.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends CustomizedRepoistory<Task,Integer>{
    @Modifying
    @Query("update Task t set t.status = '结束' where t.id = :id")
    int updateTaskStatus(@Param("id") int id);


}
