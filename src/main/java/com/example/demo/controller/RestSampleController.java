package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestSampleController {

  @RequestMapping("/rest")
  public String sample2() {
    return "Gradle Spring Boot Rest";
  }
}