package com.example.springbootend.service;

import com.example.springbootend.entity.Lession;
import com.example.springbootend.repository.LessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LessionService {
    @Autowired
    private LessionRepository lessionRepository;

    public Lession add(Lession lession){
        lessionRepository.save(lession);
        return lessionRepository.refresh(lession);
    }

    public List<Lession> findAllLessions(){
        return lessionRepository.findAllById();
    }
}
