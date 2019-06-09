package com.example.springbootend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Lession course;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime deadTime;

    private int numOfPerson;

    private String place;

    private int status = -1;

    @Column(columnDefinition = "DATETIME NOT NULL " +
            "DEFAULT CURRENT_TIMESTAMP",
            updatable = false ,
            insertable =  false)
    private LocalDateTime insertTime;

}
