package com.example.demo.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.todo.entity.Todo;
import com.example.demo.todo.form.TodoCreateForm;
import com.example.demo.todo.form.TodoUpdateForm;
import com.example.demo.todo.repository.TodoRepository;
import com.example.demo.todo.service.TodoService;

@Controller
@RequestMapping("/todo")
public class TodoController {

  @Autowired
  TodoService todoService;

  @Autowired
  TodoRepository todoRepository;

  private static final String PREFIX = "todo/";
  private static final String REDIRECT_URL = "redirect:/todo/list";

  @GetMapping("list")
  public String displayList(Model model) {
    List<Todo> todolist = todoService.getTodoList();

    model.addAttribute("todolist", todolist);

    return PREFIX + "list";
  }

  @GetMapping("detail/{id}")
  public String displayDetail(Model model, @PathVariable Long id) {
    Todo todo = todoService.getTodo(id);
    
    model.addAttribute("todo", todo);

    return PREFIX + "detail";
  }

  @GetMapping("create")
  public String displayCreate(Model model) {
    return PREFIX + "create";
  }

  @GetMapping("update")
  public String displayUpdate(Model model) {
    return PREFIX + "update";
  }

  @PostMapping("create")
  public String execCreate(Model model, @RequestBody TodoCreateForm request) {
    todoService.create(request);

    return REDIRECT_URL;
  }

  @PostMapping("update")
  public String execUpdate(Model model, @RequestBody TodoUpdateForm request) {
    todoService.update(request);

    return REDIRECT_URL;
  }

  @PostMapping("delete/{id}")
  public String execDelete(Model model, @PathVariable Long id) {
    todoRepository.deleteById(id);
    
    return REDIRECT_URL;
  }

  @PostMapping("done/{id}")
  public String execDone(Model model, @PathVariable Long id) {
    todoService.done(id);

    return REDIRECT_URL;
  }

  @GetMapping("user/{userId}")
  public String displayUsersList(Model model, @PathVariable Long userId) {
    List<Todo> todolist = todoService.getTodoListByUserId(userId);

    model.addAttribute("todolist", todolist);

    return PREFIX + "list";
  }
}