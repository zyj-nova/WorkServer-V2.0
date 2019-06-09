package com.example.springbootend.repository;

import com.example.springbootend.entity.Lession;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessionRepository extends CustomizedRepoistory<Lession,Integer> {

    @Query("select lession from Lession lession")
    List<Lession> findAllById();
}
