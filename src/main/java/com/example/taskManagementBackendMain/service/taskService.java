package com.example.taskManagementBackendMain.service;

import com.example.taskManagementBackendMain.entity.Task;
import com.example.taskManagementBackendMain.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class taskService {


    @Autowired
    private TaskRepo taskRepo;

    public Task addTasks(Task task){
        task.setCompleted(false); // default value
        return taskRepo.save(task);
    }

    public List<Task> getAllTask(){
        return taskRepo.findAll();
    }
    // Get Task by ID
    public Task getTaskById(Long id){
        return taskRepo.findById(id).orElse(null);
    }


    public Task updateTask(Long id , Task updateTask){
        Task task = taskRepo.findById(id).orElse(null);

        if(task != null){
            task.setTitle(updateTask.getTitle());
            task.setDescription(updateTask.getDescription());
            task.setCompleted(updateTask.isCompleted());
            return taskRepo.save(task);
        }
        return null;
    }

    public void deleteTask(Long id){
        taskRepo.deleteById(id);
    }


    public Task markCompleted(Long id){
        Task task = taskRepo.findById(id)
                .orElseThrow(()-> new RuntimeException("Task not found"));
        task.setCompleted(true);
        return taskRepo.save(task);
    }


}
