package com.example.springbootend.service;

import com.example.springbootend.entity.*;
import com.example.springbootend.repository.ExamDetailRepository;
import com.example.springbootend.repository.ExamRepository;
import com.example.springbootend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneOffset;
import java.util.*;

@Slf4j
@Service
@Transactional
public class ExamService {
    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamDetailRepository examDetailRepository;

    @Autowired
    private UserRepository userRepository;



    // 同一时间同一地点不允许有 2 个监考；
    // 没有冲突 == false
    // 冲突 == true
    private boolean checkTimeAndPlaceConflict(Exam exam) {
        List<Exam> exams = examRepository.findByPlace(exam.getPlace());
        if (exams.size() < 1)
            return false;
        for (Exam e : exams) {
            if (checkTimeConflict(e, exam))
                return true;
        }
        return false;
    }

    // true = 冲突
    private boolean checkTimeConflict(Exam e1, Exam e2) {
        return Math.abs(e1.getDeadTime().toEpochSecond(ZoneOffset.of("+8")) - e1.getStartTime().toEpochSecond(ZoneOffset.of("+8")))
                > Math.abs(e2.getStartTime().toEpochSecond(ZoneOffset.of("+8")) - e1.getStartTime().toEpochSecond(ZoneOffset.of("+8")));
    }

    public Exam addExam(Exam exam) throws Exception {
        if (checkTimeAndPlaceConflict(exam)) {//存在冲突
            log.debug("{}", "检测到冲突");
            throw new Exception("冲突");
        } else {
            examRepository.save(exam);
            return examRepository.refresh(exam);
        }

    }

    public List<Exam> listExams() {
        return examRepository.findAll();
    }

    //获取推荐老师列表
    public List<User> getRecommendTeachers() {
        List<ExamDetail> details = examDetailRepository.findAll();
        List<User> users = userRepository.findAll();
        TreeMap<Integer, User> treeMap = new TreeMap<>();
        HashMap<User, Integer> hashMap = new HashMap<>();
        List<User> lists = new ArrayList<>();
        List<SortTeacher> list = new ArrayList<>();
        for (User u : users) {
            SortTeacher s = new SortTeacher();
            s.setTeacher(u);
            s.setCount(0);
            list.add(s);
            //hashMap.put(u, 0);
        }

        for (ExamDetail detail : details) {
            User u = detail.getTeacher();
            for(int i = 0; i < list.size(); i++){
                if (list.get(i).getTeacher().getId() == u.getId()){
                    list.get(i).setCount(list.get(i).getCount()+1);
                }
            }
        }
        Collections.sort(list, new Comparator<SortTeacher>() {
            @Override
            public int compare(SortTeacher o1, SortTeacher o2) {
                if (o1.getCount() < o2.getCount())
                    return -1;
                else if (o1.getCount() > o2.getCount())
                    return 1;
                else
                    return 0;
            }
        });
        for(int i = 0; i < list.size(); i++){
            lists.add(list.get(i).getTeacher());
            System.out.println("姓名:"+list.get(i).getTeacher().getName() + ",监考次数:" + list.get(i).getCount());
        }
        return lists;
    }

    //删除监考
    public void deleteExam(Exam exam){
        examRepository.delete(exam);
    }
//
//    //更新监考的状态,是否分配了老师
//    public void updateExamStatus(int eid){
//        examRepository.updateStatus("已分配",eid);
//    }
}
