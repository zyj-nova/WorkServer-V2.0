package com.example.springbootend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;

    //任务状态,方便管理员修改任务状态
    //1 表示任务尚未结束，0 表示任务结束，不可以回复
    private int status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadLineTime;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;
}
