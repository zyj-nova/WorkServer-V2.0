package com.example.springbootend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class Reply {
    private int id;
    private String name;
    private String comment;
    private String status;
    private String title;
}
