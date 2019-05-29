package com.example.springbootend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class User {
    public static final int USER_AUTHORITY = 1;
    public static final int ADMIN_AUTHORITY = 2;

    public static final int JIANGSHI = 1;
    public static final int FUJIAOSHOU = 2;
    public static final int JIAOSHOU = 3;
    public static final int GONGCHENGSHI = 4;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String number;
    private String name;
    @Length(max = 11)
    private String phone;

    private int title = 1;
    @Column(columnDefinition = "TEXT")
    private String introduction;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    // 在没有声明时默认为1
    private int authority = 1;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;

    public User(int id) {
        this.id = id;
    }
}
