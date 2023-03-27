package com.example.demo.todo.form;

import java.time.LocalDateTime;

import com.example.demo.user.entity.User;

import lombok.Data;

@Data
public class TodoCreateForm {
  
  private Long id;
  private User user;
  private String content;
  private LocalDateTime deadline;
  private Boolean done;
}
