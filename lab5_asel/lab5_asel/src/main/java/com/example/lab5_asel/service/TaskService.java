package com.example.lab5_asel.service;

import com.example.lab5_asel.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface TaskService {

    Page<Task> getAllTasks(Pageable pageable);

    Page<Task> getTasksByUsername(String username, Pageable pageable);

    void saveTask(Task task);

    Task getTaskById(Long id);

    void deleteTaskById(Long id);

    Page<Task> getTasksByStatus(String username, String status, Pageable pageable);

    Page<Task> getTasksByPriority(String username, String priority, Pageable pageable);

    Page<Task> getTasksByStatusAndPriority(String username, String status, String priority, Pageable pageable);

    Page<Task> getTasksByStatusAndPriorityForAdmin(String status, String priority, Pageable pageable);

    Page<Task> getTasksByPriorityForAdmin(String priority, Pageable pageable);

    Page<Task> getTasksByStatusForAdmin(String status, Pageable pageable);

    Page<Task> searchTasks(String keyword, Pageable pageable);
}






//import com.example.lab5_asel.model.Task;
//import java.util.List;
//
//public interface TaskService {
//    List<Task> getAllTasks();
//
//    List<Task> getTasksByUsername(String username);
//
//    void saveTask(Task task);
//
//    Task getTaskById(Long id);
//
//    void deleteTaskById(Long id);
//
//    List<Task> getTasksByStatus(String username, String status);
//
//    List<Task> getTasksByPriority(String username, String priority);
//
//    List<Task> getTasksByStatusAndPriority(String username, String status, String priority);
//}
