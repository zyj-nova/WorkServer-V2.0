package com.example.springbootend.controller;

import com.example.springbootend.entity.*;
import com.example.springbootend.service.CourseService;
import com.example.springbootend.service.HomeworkService;
import com.example.springbootend.service.TaskDetailService;
import com.example.springbootend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private HomeworkService homeworkService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskDetailService taskDetailService;
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

    @PostMapping("/new")
    public Map postUser(@RequestBody User user){
        userService.addUser(user);
        return Map.of("user",user);
    }

    @GetMapping("/users")
    public Map getNonAdminUsers(){
        List<User> list = userService.listNonAdminUsers();
        return Map.of("users",list);
    }

    @PostMapping("/task/{taskid}/reply")
    public Map postReply(@PathVariable int taskid, @RequestBody Reply reply, @RequestAttribute int uid){
        log.debug("{}",reply.getComment());
        TaskDetail detail = taskDetailService.addReply(taskid,uid,reply);
        return Map.of("taskDetail",detail);
    }
}
