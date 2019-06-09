package com.example.springbootend.entity;

import lombok.Data;

@Data
public class SortTeacher {
    private User teacher;
    private int count = 0;
}
