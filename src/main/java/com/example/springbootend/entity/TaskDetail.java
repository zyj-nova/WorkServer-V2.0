package com.example.springbootend.entity;


import lombok.Data;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 用于描述
 * 哪个用户回复了哪个任务，
 * 回复了哪些内容，
 * 用户对某个任务的完成情况
 */
@Data
@Entity
public class TaskDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;
    //完成/未按时完成
    private int result;
    //回复的内容
    private String content;
    //完成时间
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP",
            updatable = false, insertable = false)
    private LocalDateTime completeTime;
}
