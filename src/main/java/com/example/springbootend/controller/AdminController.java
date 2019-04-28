package com.example.springbootend.controller;

import com.example.springbootend.entity.Course;
import com.example.springbootend.entity.Homework;
import com.example.springbootend.entity.User;
import com.example.springbootend.service.CourseService;
import com.example.springbootend.service.HomeworkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private HomeworkService homeworkService;

    /**
     * 添加后，将全部课程返回
     * @param course
     * @param uid
     * @return
     */
    @PostMapping("/courses")
    public Map postCourse(@RequestBody Course course, @RequestAttribute int uid) {
        course.setTeacher(new User(uid));
        courseService.addCourse(course);
        return Map.of("courses", courseService.listTeacherCourses(uid));
    }

    @PostMapping("/courses/{cid}/homeworks")
    public Map postHomework(@PathVariable int cid,
                            @RequestAttribute int uid,
                            @RequestBody Homework homework) {
        homeworkService.addHomework(homework);
        return Map.of("homeworks", homeworkService.listTeacherHomeworks(cid, uid));
    }
}
