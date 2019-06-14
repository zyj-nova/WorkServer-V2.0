package com.example.springbootend.entity;

import lombok.Data;

@Data
public class CompleteResult {
    //任务名称
    private String taskname;
    //用户
    private String username;
    //完成情况
    private String result;
    //学工号
    private String uno;
}
