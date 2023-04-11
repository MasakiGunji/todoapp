package com.example.todoapp.controllers;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.todoapp.models.Person;
import com.example.todoapp.repository.PersonRepository;

@Controller
public class PersonController {
  
  private final PersonRepository repository;
  public PersonController(PersonRepository repository){
    this.repository = repository;
  }

  @GetMapping("/")
  public String index(@ModelAttribute Person person, Model model){
    model.addAttribute("pepole", repository.findAll());
    return "person/index";
  }

  @PostMapping("/create")
  public String create(@Validated @ModelAttribute Person person, BindingResult result, Model model){
    if (result.hasErrors()){
      model.addAttribute("pepole", repository.findAll()); 
      return "person/index";
    }
    repository.saveAndFlush(person);
    return "person/create";
  }

  @GetMapping("/delete/{id}")	// 初期データの投入
  public String remove(@PathVariable long id){	
    repository.deleteById(id);	
    return "redirect:/";	
  }

  @GetMapping("/edit/{id}")
  public String edit(@PathVariable long id, Model model){
    model.addAttribute("person", repository.findById(id)); //一覧用データの用意
    return "person/edit";
  }

  @PostMapping("/update/{id}")
  public String update(@PathVariable long id, @Validated @ModelAttribute Person person, BindingResult result){
    if (result.hasErrors()){
      return "person/edit";
    }
    repository.save(person);
    return "redirect:/";
   }

  @PostConstruct
  public void dataInit(){
    Person suzuki = new Person();
    suzuki.setName("鈴木");
    suzuki.setAge(23);
    suzuki.setEmail("suzuki@email.com");
    repository.saveAndFlush(suzuki);
 
    Person sato = new Person();
    sato.setName("佐藤");
    sato.setAge(20);
    sato.setEmail("sato@email.com");
    repository.saveAndFlush(sato);
   }
}