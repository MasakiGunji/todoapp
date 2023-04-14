package com.example.todoapp.controllers;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.todoapp.models.Tasktype;
import com.example.todoapp.repository.TasktypeRepository;

@Controller
@RequestMapping("/tasktype")
public class TasktypeController {
  
  private final TasktypeRepository tasktyperepository;

  public TasktypeController(TasktypeRepository tasktyperepository){
    this.tasktyperepository = tasktyperepository;
  }

  @GetMapping()
  public String index(@ModelAttribute Tasktype tasktype, Model model){
    model.addAttribute("tasktypes", tasktyperepository.findAll());
    return "tasktype/index";
  }

  @PostMapping("/create")
  public String create(@Validated @ModelAttribute Tasktype tasktype, BindingResult result, Model model){
    if (result.hasErrors()){
      model.addAttribute("tasktypes", tasktyperepository.findAll()); 
      return "tasktype/index";
    }
    tasktyperepository.saveAndFlush(tasktype);
    return "redirect:/tasktype";
  }

  @GetMapping("/delete/{id}")
  public String remove(@PathVariable Integer id){
    tasktyperepository.deleteById(id);	
    return "redirect:/tasktype";	
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Integer id, Model model){
    model.addAttribute("tasktype", tasktyperepository.findById(id));
    return "tasktype/edit";
  }

  @PostMapping("/update/{id}")
  public String update(@PathVariable long id, @Validated @ModelAttribute Tasktype tasktype, BindingResult result){
    if (result.hasErrors()){
      return "tasktype/edit";
    }
    tasktyperepository.save(tasktype);
    return "redirect:/tasktype";
  }


  @PostConstruct
  public void dataInit(){
    Tasktype wait = new Tasktype();
    wait.setType("未着手");
    wait.setComment("まだ業務に入れていない");
    tasktyperepository.saveAndFlush(wait);
 
    Tasktype common = new Tasktype();
    common.setType("対応中");
    common.setComment("現在行っている業務");
    tasktyperepository.saveAndFlush(common);

    Tasktype emergency = new Tasktype();
    emergency.setType("完了");
    emergency.setComment("終わった業務");
    tasktyperepository.saveAndFlush(emergency);
  }
}
