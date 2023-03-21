package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.ui.Model;

@Controller
public class TestController {

  @RequestMapping("/test")
  public String sample(Model model) {
    // sampleテキストを作成して渡す
    model.addAttribute("sample", "sample text");

    return "test";
  }
}