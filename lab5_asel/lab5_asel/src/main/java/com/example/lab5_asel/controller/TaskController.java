package com.example.lab5_asel.controller;

import com.example.lab5_asel.model.Category;
import com.example.lab5_asel.model.Task;
import com.example.lab5_asel.model.Users;
import com.example.lab5_asel.service.CategoryService;
import com.example.lab5_asel.service.EmailService;
import com.example.lab5_asel.service.TaskService;
import com.example.lab5_asel.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
public class TaskController {
    private final TaskService tasksService;
    private final CategoryService categoryService;
    private final UserService usersService;
    private final EmailService emailService;


    //конструктор
    @Autowired
    public TaskController(TaskService tasksService, CategoryService categoryService,
                           UserService usersService, EmailService emailService) {
        this.tasksService = tasksService;
        this.categoryService = categoryService;
        this.usersService = usersService;
        this.emailService = emailService;
    }




    //LIST OF TASKS
    @GetMapping("/tasks")
    public String getTasks(@RequestParam(required = false) String status,
                           @RequestParam(required = false) String priority,
                           @RequestParam(required = false, defaultValue = "0") int page,
                           @RequestParam(required = false, defaultValue = "10") int size,
                           @RequestParam(required = false) String keyword,
                           Model model, Principal principal) {
        String username = principal.getName();
        Users user = usersService.findByUsername(username);
        model.addAttribute("user", user);

        Page<Task> tasks;
        Pageable pageable = PageRequest.of(page, size);

        if (status != null && !status.isEmpty() && priority != null && !priority.isEmpty()) {
            tasks = tasksService.getTasksByStatusAndPriorityForAdmin(status, priority, pageable);
        } else if (status != null && !status.isEmpty()) {
            tasks = tasksService.getTasksByStatusForAdmin(status, pageable);
        } else if (priority != null && !priority.isEmpty()) {
            tasks = tasksService.getTasksByPriorityForAdmin(priority, pageable);
        } else if (keyword != null && !keyword.isEmpty()) {
            tasks = tasksService.searchTasks(keyword, pageable);
        } else {
            tasks = tasksService.getAllTasks(pageable);
        }

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        model.addAttribute("listTasks", tasks);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", tasks.getTotalPages());
        model.addAttribute("status", status);
        model.addAttribute("priority", priority);


        return "task";
    }



    //LIST OF TASKS
    @GetMapping("/tasksUser")
    public String getUserTasks(@RequestParam(required = false) String status,
                               @RequestParam(required = false) String priority,
                               @RequestParam(required = false, defaultValue = "0") int page,
                               @RequestParam(required = false, defaultValue = "10") int size,
                               @RequestParam(required = false) String keyword,
                               Model model, Principal principal) {
        String username = principal.getName();
        Users user = usersService.findByUsername(username);
        model.addAttribute("user", user);

        Page<Task> tasks;
        Pageable pageable = PageRequest.of(page, size);


        //Filtration and Search
        if (status != null && !status.isEmpty() && priority != null && !priority.isEmpty()) {
            tasks = tasksService.getTasksByStatusAndPriority(username, status, priority, pageable);
        } else if (status != null && !status.isEmpty()) {
            tasks = tasksService.getTasksByStatus(username, status, pageable);
        } else if (priority != null && !priority.isEmpty()) {
            tasks = tasksService.getTasksByPriority(username, priority, pageable);
        } else if (keyword != null && !keyword.isEmpty()) {
            tasks = tasksService.searchTasks(keyword, pageable);
        } else {
            tasks = tasksService.getTasksByUsername(username, pageable);
        }

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        model.addAttribute("listTasks", tasks);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", tasks.getTotalPages());
        model.addAttribute("status", status);
        model.addAttribute("priority", priority);
        return "task-user";
    }




    //NEW TASK
    @GetMapping("/showNewTaskForm")
    public String showNewTaskForm(Model model, Principal principal) {
        String username = principal.getName();
        Users user = usersService.findByUsername(username);
        model.addAttribute("user", user);

        Task task = new Task();
        model.addAttribute("task", task);
        List<Users> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "addTask";
    }



    @PostMapping("/saveTask")
    public String saveTask(@Valid @ModelAttribute("task") Task task, BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "addTask";
        }

        Users assignedUser = usersService.findByUserId(task.getUser().getUserId());
        task.setUser(assignedUser);


        tasksService.saveTask(task);


        String emailMessage = String.format(
                "Hello %s,\nYou have a new task:\n\nTitle: %s\nDescription: %s\nDue Date: %s",
                assignedUser.getUsername(),
                task.getTitle(),
                task.getDescription(),
                task.getDueDate() != null ? task.getDueDate().toString() : "No due date"
        );
        emailService.sendEmail(assignedUser.getEmail(), "New Task Assigned", emailMessage);

        return "redirect:/tasks";
    }




    //UPDATE
    @GetMapping("/showFormForUpdateTask/{id}")
    public String showFormForUpdate(@PathVariable Long id, Model model, Principal principal) {
        String username = principal.getName();
        Users user = usersService.findByUsername(username);
        model.addAttribute("user", user);

        Task task = tasksService.getTaskById(id);
        model.addAttribute("task", task);
        return "updateTask";
    }



    //DELETE
    @GetMapping("/deleteTask/{id}")
    public String deleteTask(@PathVariable("id") Long id) {
        this.tasksService.deleteTaskById(id);
        return "redirect:/tasks";
    }



    //VIEW A TASK
    @GetMapping("/viewTask/{id}")
    public String viewTask(@PathVariable Long id, Model model, Principal principal) {
        String username = principal.getName();
        Users user = usersService.findByUsername(username);
        model.addAttribute("user", user);

        Task task = tasksService.getTaskById(id);
        if (task == null) {
            return "redirect:/tasks";
        }
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        List<Users> users = usersService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("task", task);
        return "viewTask";
    }

}






