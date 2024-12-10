package com.example.lab5_asel.service.implem;


import com.example.lab5_asel.repository.TaskRepository;
import com.example.lab5_asel.service.TaskService;
import com.example.lab5_asel.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository tasksRepository;

    //CONSTRUCTOR
    public TaskServiceImpl(TaskRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }


    // GET ALL TASKS
    @Override
    public Page<Task> getAllTasks(Pageable pageable) {
        return tasksRepository.findAll(pageable);
    }


    // GET LIST OF TASKS FOR EACH USERNAME
    @Override
    public Page<Task> getTasksByUsername(String username, Pageable pageable) {
        return tasksRepository.findByUserUsernameOrderByPriorityAscDueDateAsc(username, pageable);
    }


    // SAVE NEW TASK
    @Override
    public void saveTask(Task task) {
        this.tasksRepository.save(task);
    }


    // GET A TASK BY ITS ID
    @Override
    public Task getTaskById(Long id) {
        Optional<Task> task = tasksRepository.findById(id);
        Task taskn = null;
        if(task.isPresent()) {
            taskn = task.get();
        } else {
            throw new RuntimeException("User not found for id " + id);
        }
        return taskn;
    }


    // DELETE TASK
    @Override
    public void deleteTaskById(Long id) {
        this.tasksRepository.deleteById(id);
    }


    // GET TASKS BY STATUS
    @Override
    public Page<Task> getTasksByStatus(String username, String status, Pageable pageable) {
        return tasksRepository.findByUserUsernameAndStatusOrderByPriorityAscDueDateAsc(username, status, pageable);
    }


    // GET TASKS BY PRIORITY
    @Override
    public Page<Task> getTasksByPriority(String username, String priority, Pageable pageable) {
        return tasksRepository.findByUserUsernameAndPriorityOrderByPriorityAscDueDateAsc(username, priority, pageable);
    }


    // GET TASKS BY PRIORITY AND STATUS
    @Override
    public Page<Task> getTasksByStatusAndPriority(String username, String status, String priority, Pageable pageable) {
        return tasksRepository.findByUserUsernameAndStatusAndPriorityOrderByPriorityAscDueDateAsc(username, status, priority, pageable);
    }

    @Override
    public Page<Task> getTasksByStatusAndPriorityForAdmin(String status, String priority, Pageable pageable) {
        return tasksRepository.findByStatusAndPriority(status, priority, pageable);
    }

    @Override
    public Page<Task> getTasksByStatusForAdmin(String status, Pageable pageable) {
        return tasksRepository.findByStatus(status, pageable);
    }

    @Override
    public Page<Task> getTasksByPriorityForAdmin(String priority, Pageable pageable) {
        return tasksRepository.findByPriority(priority, pageable);
    }

    @Override
    public Page<Task> searchTasks(String keyword, Pageable pageable) {
        return tasksRepository.searchByKeyword(keyword, pageable);
    }

}














//import com.example.lab5_asel.model.Task;
//import com.example.lab5_asel.repository.TaskRepository;
//import com.example.lab5_asel.service.TaskService;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class TaskServiceImpl implements TaskService {
//
//    private final TaskRepository tasksRepository;
//
//    public TaskServiceImpl(TaskRepository tasksRepository) {
//        this.tasksRepository = tasksRepository;
//    }
//
//    @Override
//    public List<Task> getAllTasks() {
//        return tasksRepository.findAll();
//    }
//
//    @Override
//    public List<Task> getTasksByUsername(String username) {
//        return tasksRepository.findByUserUsernameOrderByPriorityAscDueDateAsc(username);
//    }
//
//    @Override
//    public void saveTask(Task task) {
//        this.tasksRepository.save(task);
//    }
//
//    @Override
//    public Task getTaskById(Long id) {
//        Optional<Task> task = tasksRepository.findById(id);
//        Task taskn = null;
//        if(task.isPresent()) {
//            taskn = task.get();
//        } else {
//            throw new RuntimeException("User not found for id " + id);
//        }
//        return taskn;
//    }
//
//    @Override
//    public void deleteTaskById(Long id) {
//        this.tasksRepository.deleteById(id);
//    }
//
//    @Override
//    public List<Task> getTasksByStatus(String username, String status) {
//        return tasksRepository.findByUserUsernameAndStatusOrderByPriorityAscDueDateAsc(username, status);
//    }
//
//    @Override
//    public List<Task> getTasksByPriority(String username, String priority) {
//        return tasksRepository.findByUserUsernameAndPriorityOrderByPriorityAscDueDateAsc(username, priority);
//    }
//
//    @Override
//    public List<Task> getTasksByStatusAndPriority(String username, String status, String priority) {
//        return tasksRepository.findByUserUsernameAndStatusAndPriorityOrderByPriorityAscDueDateAsc(username, status, priority);
//    }
//}
