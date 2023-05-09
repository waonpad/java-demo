package com.example.demo.todo.form;

import java.time.LocalDate;

// import com.example.demo.user.entity.User;

import lombok.Data;

@Data
public class TodoCreateForm {
  
  private String content;
  private LocalDate deadline;
}
