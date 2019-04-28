package com.example.springbootend.service;

import com.example.springbootend.entity.Course;
import com.example.springbootend.repository.CourseDetailRepoistory;
import com.example.springbootend.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseDetailRepoistory courseDetailRepoistory;

    /**
     * 获取指定教师的所有课程
     * @param tid
     * @return
     */
    public List<Course> listTeacherCourses(int tid) {
        return courseRepository.list(tid);
    }

    public List<Course> listStudentCourses(int sid) {
        return courseDetailRepoistory.list(sid);
    }

    public Course addCourse(Course course) {
        courseRepository.save(course);
        return courseRepository.refresh(course);
    }

    public Course getTeacherCourse(int cid, int tid) {
        return courseRepository.find(cid, tid);
    }

    public Course getStudentCourse(int cid, int sid) {
        return courseDetailRepoistory.find(cid, sid);
    }
}
