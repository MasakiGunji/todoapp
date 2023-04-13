package com.example.todoapp.controllers;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todoapp.models.Task;
import com.example.todoapp.models.Tasktype;
import com.example.todoapp.repository.TaskRepository;
import com.example.todoapp.repository.TasktypeRepository;

@Controller
@RequestMapping("/task")
public class TaskController {

  private final TaskRepository taskrepository;
  private final TasktypeRepository tasktyperepository;

  public TaskController(TaskRepository taskrepository,TasktypeRepository tasktyperepository){
    this.taskrepository = taskrepository;
    this.tasktyperepository = tasktyperepository;
  }

  @GetMapping()
  public String index(@ModelAttribute Task task, Model taskmodel, Model tasktypemodel){
    taskmodel.addAttribute("tasks", taskrepository.findAll());
    tasktypemodel.addAttribute("tasktypes", tasktyperepository.findAll());
    return "task/index";
  }

  @PostMapping("/create")
  public String create(@Validated @ModelAttribute Task task, BindingResult result, Model taskmodel, Model tasktypemodel){
    if (result.hasErrors()){
      taskmodel.addAttribute("taskts", taskrepository.findAll());
      tasktypemodel.addAttribute("tasktypes", tasktyperepository.findAll());
      return "task/index";
    }
    taskrepository.saveAndFlush(task);
    return "redirect:/task";
  }

  // @PostConstruct
  // public void dataInit(){
  //   Tasktype wait = new Tasktype();
  //   wait.setType("待ち");
  //   wait.setComment("待ち状態");
  //   tasktyperepository.saveAndFlush(wait);
}
