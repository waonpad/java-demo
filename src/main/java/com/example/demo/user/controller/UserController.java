package com.example.demo.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.entity.User;
import com.example.demo.user.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("list")
  public String displayList(Model model) {
    List<User> userlist = userService.searchAll();
    model.addAttribute("userlist", userlist);
    return "user/list";
  }
}