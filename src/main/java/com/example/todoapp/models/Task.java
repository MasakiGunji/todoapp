package com.example.todoapp.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToOne()
  @JoinColumn(name = "tasktype_id")
  private Tasktype tasktype;

  @NotBlank
  private String title;

  @NotBlank
  private String text;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate deadline;


}
