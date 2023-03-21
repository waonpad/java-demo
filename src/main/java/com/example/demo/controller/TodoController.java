package com.example.demo.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

import com.example.demo.model.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.demo.util.Util;

@Controller
public class TodoController {
  private static final String TodosJsonPath = "src/main/resources/todos.json";
  
  @GetMapping("/todo")
  public String index(Model model) {

    String todosJson = Util.readJsonAsString(TodosJsonPath);

    ObjectMapper mapper = new ObjectMapper();
    try {
      Todo[] todos = mapper.readValue(todosJson, Todo[].class);

      model.addAttribute("todos", todos);
      return "todo";
    } catch (IOException e) {
      e.printStackTrace();
      return "error";
    }
  }

  @PostMapping("/todo/create")
  public String create(String title) {

    Todo todo = 
      new Todo() {{
        setId(UUID.randomUUID().hashCode());
        setTitle(title);
        setCompleted(false);
        setCreatedAt(new Date());
      }};
    
    String todosJson = Util.readJsonAsString(TodosJsonPath);

    ObjectMapper mapper = new ObjectMapper();
    try {
      Todo[] todos = mapper.readValue(todosJson, Todo[].class);
      List<Todo> todosList = new ArrayList<>(Arrays.asList(todos));

      todosList.add(todo);

      String updatedTodos = mapper.writeValueAsString(todosList);

      File updatedTodosJson = new File(TodosJsonPath);
      FileWriter writer = new FileWriter(updatedTodosJson, false);

      writer.write(updatedTodos);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "redirect:/todo";
  }

  @PostMapping("/todo/delete")
  public String delete(int id) {
    String todosJson = Util.readJsonAsString(TodosJsonPath);

    ObjectMapper mapper = new ObjectMapper();
    try {
      Todo[] todos = mapper.readValue(todosJson, Todo[].class);
      List<Todo> todosList = new ArrayList<>(Arrays.asList(todos));

      todosList.removeIf(todo -> todo.getId() == id);

      String updatedTodos = mapper.writeValueAsString(todosList);

      File updatedTodosJson = new File(TodosJsonPath);
      FileWriter writer = new FileWriter(updatedTodosJson, false);

      writer.write(updatedTodos);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "redirect:/todo";
  }

  @PostMapping("/todo/update")
  public String update(int id, String title) {
    String todosJson = Util.readJsonAsString(TodosJsonPath);

    ObjectMapper mapper = new ObjectMapper();
    try {
      Todo[] todos = mapper.readValue(todosJson, Todo[].class);
      List<Todo> todosList = new ArrayList<>(Arrays.asList(todos));

      todosList.stream()
        .filter(todo -> todo.getId() == id)
        .forEach(todo -> {
          todo.setTitle(title);
        });

      String updatedTodos = mapper.writeValueAsString(todosList);

      File updatedTodosJson = new File(TodosJsonPath);
      FileWriter writer = new FileWriter(updatedTodosJson, false);

      writer.write(updatedTodos);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "redirect:/todo";
  }

  @PostMapping("/todo/completeToggle")
  public String complete(int id) {
    String todosJson = Util.readJsonAsString(TodosJsonPath);

    ObjectMapper mapper = new ObjectMapper();
    try {
      Todo[] todos = mapper.readValue(todosJson, Todo[].class);
      List<Todo> todosList = new ArrayList<>(Arrays.asList(todos));

      todosList.stream()
        .filter(todo -> todo.getId() == id)
        .forEach(todo -> {
          todo.setCompleted(!todo.isCompleted());
        });

      String updatedTodos = mapper.writeValueAsString(todosList);

      File updatedTodosJson = new File(TodosJsonPath);
      FileWriter writer = new FileWriter(updatedTodosJson, false);

      writer.write(updatedTodos);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "redirect:/todo";
  }
}
