package com.example.taskManagementBackendMain.repository;

import com.example.taskManagementBackendMain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface TaskRepo extends JpaRepository<Task,Long> {
}
