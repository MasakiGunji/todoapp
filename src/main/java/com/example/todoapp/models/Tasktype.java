package com.example.todoapp.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
  
@Data
@Entity
public class Tasktype {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @NotBlank
  @Size(max = 120)
  private String type;

  @NotBlank
  @Size(max = 120)
  private String comment;

  @OneToMany(mappedBy = "tasktype")
  private List<Task> tasks = new ArrayList<>();
}
