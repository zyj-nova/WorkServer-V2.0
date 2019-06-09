package com.example.springbootend.entity;

import lombok.Data;

import java.util.List;

@Data
public class Examing {
    private List<Integer> tids;

    private Exam exam;
}
