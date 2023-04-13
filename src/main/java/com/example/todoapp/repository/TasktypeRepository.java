package com.example.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.models.Tasktype;

public interface TasktypeRepository  extends JpaRepository<Tasktype, Integer>{

}
