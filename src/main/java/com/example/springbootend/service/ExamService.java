package com.example.springbootend.service;

import com.example.springbootend.entity.Exam;
import com.example.springbootend.entity.ExamDetail;
import com.example.springbootend.entity.SortTeacher;
import com.example.springbootend.entity.User;
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

    public int getExamTimesByTeacher(User teacher) {
        List<Exam> exams = examDetailRepository.findByTeacher(teacher.getId());
        if (exams.size() == 1)
            return 1;
        if (exams.size() == 0)
            return 0;
        exams.sort(new Comparator<Exam>() {
            @Override
            public int compare(Exam o1, Exam o2) {
                if (o1.getStartTime().toEpochSecond(ZoneOffset.of("+8"))
                        < o2.getStartTime().toEpochSecond(ZoneOffset.of("+8"))) {
                    return 1;
                } else if (o1.getStartTime().toEpochSecond(ZoneOffset.of("+8"))
                        == o2.getStartTime().toEpochSecond(ZoneOffset.of("+8"))) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        int count = 0;
        for (Exam e : exams) {
            for (Exam e2 : exams) {
                if (checkTimeConflict(e, e2)) {
                    //出现冲突
                    count++;
                }
            }
        }
        return count;
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

    public int count(int tid) {
        return examDetailRepository.times(tid);
    }

    //更新指定id的监考数据
    public Exam updateExam(Exam newExam) {
        Exam exam = examRepository.merge(newExam);
        examRepository.save(exam);
        return examRepository.refresh(newExam);
    }


}
