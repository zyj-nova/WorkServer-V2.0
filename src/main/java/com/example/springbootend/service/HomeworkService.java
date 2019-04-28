package com.example.springbootend.service;

import com.example.springbootend.entity.Homework;
import com.example.springbootend.repository.HomeworkDetailRepository;
import com.example.springbootend.repository.HomeworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HomeworkService {
    @Autowired
    private HomeworkRepository homeworkRepository;
    @Autowired
    private HomeworkDetailRepository homeworkDetailRepository;

    public Homework addHomework(Homework homework) {
        homeworkRepository.save(homework);
        return homeworkRepository.refresh(homework);
    }
    public List<Homework> listTeacherHomeworks(int cid, int tid) {
        return homeworkRepository.list(cid, tid);
    }

    public List<Homework> listStudentHomeworks(int cid, int sid) {
        return homeworkRepository.listByStudent(cid, sid);
    }
}
