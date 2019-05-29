package com.example.springbootend.service;

import com.example.springbootend.entity.Reply;
import com.example.springbootend.entity.Task;
import com.example.springbootend.entity.TaskDetail;
import com.example.springbootend.entity.User;
import com.example.springbootend.repository.TaskDetailRepository;
import com.example.springbootend.repository.TaskRepository;
import com.example.springbootend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskDetailService {
    @Autowired
    private TaskDetailRepository taskDetailRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public TaskDetail addReply(int taskId, int userId, Reply reply){
        TaskDetail detail = new TaskDetail();
        Task t = taskRepository.findById(taskId).get();
        User u = userRepository.findById(userId);
        detail.setContent(reply.getComment());
        detail.setTask(t);
        detail.setUser(u);

        detail.setResult(1);
        taskDetailRepository.save(detail);
        return taskDetailRepository.refresh(detail);
    }


}
