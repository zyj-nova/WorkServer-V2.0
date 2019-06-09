package com.example.springbootend.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class ExamDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private User teacher;

    @ManyToOne
    private Exam exam;

    @Column(columnDefinition = "DATETIME NOT NULL " +
            "DEFAULT CURRENT_TIMESTAMP",
            updatable = false ,
            insertable =  false)
    private LocalDateTime insertTime;

}
