package com.example.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.todoapp.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
