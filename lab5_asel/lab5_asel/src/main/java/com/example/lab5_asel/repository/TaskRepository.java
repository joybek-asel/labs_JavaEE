package com.example.lab5_asel.repository;


import com.example.lab5_asel.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Page<Task> findAll(Pageable pageable);

    // ORDER THE LIST OF TASKS BY DUEDATE AND PRIORITY
    Page<Task> findByUserUsernameOrderByPriorityAscDueDateAsc(String username, Pageable pageable);

    // FIND TASKS BY USERNAME AND STATUS, ORDERED BY PRIORITY
    Page<Task> findByUserUsernameAndStatusOrderByPriorityAscDueDateAsc(String username, String status, Pageable pageable);

    // FIND TASKS BY USERNAME AND PRIORITY
    Page<Task> findByUserUsernameAndPriorityOrderByPriorityAscDueDateAsc(String username, String priority, Pageable pageable);

    // FIND TASKS BY USERNAME, STATUS AND PRIORITY
    Page<Task> findByUserUsernameAndStatusAndPriorityOrderByPriorityAscDueDateAsc(String username, String status, String priority, Pageable pageable);

    Page<Task> findByStatus(String status, Pageable pageable);
    Page<Task> findByPriority(String priority, Pageable pageable);
    Page<Task> findByStatusAndPriority(String status, String priority, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.title LIKE %:keyword% OR t.description LIKE %:keyword%")
    Page<Task> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

}



