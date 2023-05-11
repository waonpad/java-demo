package com.example.demo.todo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.todo.entity.Todo;
import com.example.demo.todo.form.TodoCreateForm;
import com.example.demo.todo.form.TodoUpdateForm;
import com.example.demo.todo.service.TodoService;
import com.example.demo.user.service.UserDetailsImpl;

@Controller
@RequestMapping("/todo")
public class TodoController {

  @Autowired
  TodoService todoService;

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
  public String displayCreate(Model model, @ModelAttribute("form") TodoCreateForm form) {
    return PREFIX + "create";
  }

  @GetMapping("update/{id}")
  public String displayUpdate(Model model, @ModelAttribute("form") TodoUpdateForm form, @PathVariable Long id) {
    Todo todo = todoService.getTodo(id);

    model.addAttribute("todo", todo);

    return PREFIX + "update";
  }

  @PostMapping("create")
  public String execCreate(Model model, @ModelAttribute("form") @Validated TodoCreateForm form, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    todoService.create(form, userDetails.getUser());

    return REDIRECT_URL;
  }

  @PostMapping("update")
  public String execUpdate(Model model, @ModelAttribute("form") @Validated TodoUpdateForm form, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    todoService.update(form, userDetails.getUser());

    return REDIRECT_URL;
  }

  // 本来は権限チェックが必要
  @PostMapping("delete/{id}")
  public String execDelete(Model model, @PathVariable Long id) {
    todoService.delete(id);

    return REDIRECT_URL;
  }

  // 本来は権限チェックが必要
  @PostMapping("done/{id}")
  public String execDone(Model model, @PathVariable Long id) {
    todoService.done(id);

    return REDIRECT_URL;
  }

  @PostMapping("help/{id}")
  public String execHelp(Model model, @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    todoService.help(id, userDetails.getUser());

    return REDIRECT_URL;
  }

  @PostMapping("unhelp/{id}")
  public String execUnHelp(Model model, @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
    todoService.unHelp(id, userDetails.getUser());

    return REDIRECT_URL;
  }

  @GetMapping("user/{userId}")
  public String displayUsersList(Model model, @PathVariable Long userId) {
    List<Todo> todolist = todoService.getTodoListByUserId(userId);

    model.addAttribute("todolist", todolist);

    return PREFIX + "list";
  }
}