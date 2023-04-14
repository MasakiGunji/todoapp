package com.example.todoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.todoapp.models.Task;
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
  public String index(@ModelAttribute Task task, Model model){
    model.addAttribute("tasks", taskrepository.findAll());
    model.addAttribute("tasktypes", tasktyperepository.findAll());
    model.addAttribute("tasks1", taskrepository.findByTasktype(tasktyperepository.getOne(1)));
    model.addAttribute("tasks2", taskrepository.findByTasktype(tasktyperepository.getOne(2)));
    model.addAttribute("tasks3", taskrepository.findByTasktype(tasktyperepository.getOne(3)));
    
    return "task/index";
  }

  @PostMapping("/create")
  public String create(@Validated @ModelAttribute Task task, BindingResult result, Model model){
    if (result.hasErrors()){
      model.addAttribute("tasks", taskrepository.findAll());
      model.addAttribute("tasktypes", tasktyperepository.findAll());
      return "task/index";
    }
    taskrepository.saveAndFlush(task);
    return "redirect:/task";
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model){
    model.addAttribute("task", taskrepository.findById(id));
    model.addAttribute("tasktypes", tasktyperepository.findAll());
    return "task/edit";
  }

  @PostMapping("/update/{id}")
  public String update(@PathVariable Integer id, @Validated @ModelAttribute Task task, BindingResult result){
    if (result.hasErrors()){
      return "task/edit";
    }
    taskrepository.save(task);
    return "redirect:/task";
  }
}
