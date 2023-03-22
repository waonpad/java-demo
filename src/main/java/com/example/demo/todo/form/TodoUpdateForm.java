package com.example.demo.todo.form;

import java.util.Date;

import lombok.Data;

@Data
public class TodoUpdateForm {
  
  private Long id;
  private String content;
  private Date deadline;
}
