package com.example.springbootend.service;


import com.example.springbootend.entity.Exam;
import com.example.springbootend.entity.ExamDetail;
import com.example.springbootend.entity.Examing;
import com.example.springbootend.entity.User;
import com.example.springbootend.repository.ExamDetailRepository;
import com.example.springbootend.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.util.List;

@Service
@Transactional
public class ExamDetailService {
    @Autowired
    private ExamDetailRepository examDetailRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private UserService userService;

    public ExamDetail addExamDetail(ExamDetail detail){
        examDetailRepository.save(detail);
        return examDetailRepository.refresh(detail);
    }
    //获取某一场监考的监考老师
    public List<User> listExamTeacher(Exam exam){
        return examDetailRepository.findExamTeacher(exam.getId());
    }

    //删除监考详细信息，
    public ExamDetail deleteExamDetail(ExamDetail examDetail){
        examDetailRepository.delete(examDetail);
        return examDetail;
    }
    // true = 冲突
    private boolean checkTimeConflict(Exam e1, Exam e2) {
        return Math.abs(e1.getDeadTime().toEpochSecond(ZoneOffset.of("+8")) - e1.getStartTime().toEpochSecond(ZoneOffset.of("+8")))
                > Math.abs(e2.getStartTime().toEpochSecond(ZoneOffset.of("+8")) - e1.getStartTime().toEpochSecond(ZoneOffset.of("+8")));
    }

    //分配监考教师时考虑是否时间上冲突
    //false 不冲突
    //true 冲突
    private boolean checkTeacherConflict(User teacher,Exam exam) {
        List<Exam> exams = examDetailRepository.findByTeacher(teacher.getId());
        if (exams.size() == 0)
            return false;
        for(Exam e1:exams){
          if (checkTimeConflict(e1,exam)){
              return true;
          }
        }
        return false;
    }
    //分配监考老师
    public boolean addExamTeacher(Examing examing){
        boolean flag = false;
        List<Integer> ids = examing.getTids();
        Exam exam = examing.getExam();
        for(Integer u:ids){
            User user = userService.getUserById(u);
            if (checkTeacherConflict(user,exam)){
                System.out.println("冲突!");
               flag = true;
            }
            ExamDetail detail = new ExamDetail();
            examRepository.updateStatus("已分配",exam.getId());
            detail.setExam(exam);
            detail.setTeacher(user);
            addExamDetail(detail);
        }
        //不冲突返回false
        return flag;
    }
}
