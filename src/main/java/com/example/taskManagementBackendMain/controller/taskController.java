package com.example.taskManagementBackendMain.controller;

import com.example.taskManagementBackendMain.entity.Task;
import com.example.taskManagementBackendMain.service.taskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")

public class taskController {

    @Autowired
    private taskService taskService;

    @PostMapping
    public Task createTask(@RequestBody Task task){
        return taskService.addTasks(task);
    }

    @GetMapping
    public List<Task> getAllTasks(){
        return taskService.getAllTask();
    }
    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task task){
        return taskService.updateTask(id,task);
    }
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @PatchMapping("/{id}/complete")
    public Task markCompleted(@PathVariable Long id){
        return  taskService.markCompleted(id);
    }


}
