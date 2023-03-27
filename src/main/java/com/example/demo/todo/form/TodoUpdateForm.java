package com.example.demo.todo.form;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TodoUpdateForm {
  
  private Long id;
  private String content;
  private LocalDateTime deadline;
}
