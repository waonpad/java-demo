package com.example.demo.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

  @GetMapping("/")
  public String index() {
    return "index";
  }
  
  @GetMapping("/general")
  public String general() {
    return "general";
  }

  @GetMapping("/admin")
  public String admin() {
    return "admin";
  }
  
  @GetMapping("/403")
  public String error403() {
    return "error/403";
  }
}