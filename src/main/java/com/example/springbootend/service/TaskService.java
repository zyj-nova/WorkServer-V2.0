package com.example.springbootend.service;


import com.example.springbootend.entity.Task;
import com.example.springbootend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task addTask(Task task){
        taskRepository.save(task);
        return taskRepository.refresh(task);
    }
    public Task deleteTask(Task task){
        taskRepository.delete(task);
        return task;
    }

    //修改任务状态,关闭任务
    public int updateTaskStatus(Task task){
        int id = task.getId();
        int p = taskRepository.updateTaskStatus(id);
        return p;
    }

    //获取所有任务
    public List<Task> listAllTasks(){
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    //更新任务信息
    public Task updateTask(Task t){
        Task task = taskRepository.merge(t);
        return taskRepository.save(task);

    }

}
