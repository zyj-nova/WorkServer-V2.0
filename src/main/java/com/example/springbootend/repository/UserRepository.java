package com.example.springbootend.repository;

import com.example.springbootend.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CustomizedRepoistory<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.number=:number")
    User find(@Param("number") String number);

    @Query("select u from User u WHERE u.authority = :authority")
    List<User> findAllByUserAuthority(@Param("authority") int authority);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findById(@Param("id") int id);

    @Modifying
    @Query("update User u set u.password =:pass where u.id =:uid")
    int updatePassword(@Param("pass") String password,@Param("uid") int uid);
//
//    @Modifying
//    @Query("update User u set u.authority = :aid where u.id = :id")
//    int updateAuthority(@Param("aid") Integer aid, @Param("id") Integer uid);

}
