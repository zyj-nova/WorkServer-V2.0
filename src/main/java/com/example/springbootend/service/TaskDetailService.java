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
import org.w3c.dom.ls.LSException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

    public List<Reply> getAllReply(int tid){
        List<Reply> replies = new ArrayList<>();
        List<TaskDetail> tasks = taskDetailRepository.queryReplyContent(tid);
       List<User> users = taskDetailRepository.queryReplyUSer(tid);
       for (int i = 0; i < tasks.size();i++){
          Reply r = new Reply();
          r.setName(users.get(i).getName());
          r.setComment(tasks.get(i).getContent());
          replies.add(r);
       }
       return replies;
    }


}
