package com.example.todoapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.models.Task;
import com.example.todoapp.models.Tasktype;

public interface TaskRepository extends JpaRepository<Task, Integer> {
  List<Task> findByTasktype(Tasktype tasktype);
}

